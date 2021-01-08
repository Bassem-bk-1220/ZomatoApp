package com.hb.zomato_app.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hb.zomato_app.R
import com.hb.zomato_app.data.RestaurantObjectResponse
import com.hb.zomato_app.home.DetailsRestaurantsActivity
import com.hb.zomato_app.utils.RESTAURANT_ID
import kotlinx.android.synthetic.main.item_restaurant.view.*
import me.zhanghai.android.materialratingbar.MaterialRatingBar

class ListRestaurantAdapter
    (
    private val listRestaurant: List<RestaurantObjectResponse>,
    private val activity: Activity,
    private val layout : Int = R.layout.item_restaurant
) :
    RecyclerView.Adapter<ListRestaurantAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.stars.isEnabled = false
        val numStars = listRestaurant[position].restaurant.user_rating.aggregate_rating.toFloat()

        holder.stars.rating = numStars
        holder.tvTitle.text = listRestaurant[position].restaurant.name
        holder.tvOpen.text = listRestaurant[position].restaurant.timings

        if (listRestaurant[position].restaurant.thumb.isEmpty()) {
            holder.imgrestaurant.load(R.drawable.placeholder)
        } else {
            holder.imgrestaurant.load(listRestaurant[position].restaurant.thumb)

        }
    }

    override fun getItemCount(): Int {
        return listRestaurant.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tv_title
        val tvOpen: TextView = view.tv_open
        val imgrestaurant: ImageView = view.img_restaurant
        val stars: MaterialRatingBar = view.stars

        init {
            itemView.setOnClickListener {
                val intent = Intent(activity, DetailsRestaurantsActivity::class.java)
                intent.putExtra(RESTAURANT_ID,listRestaurant[adapterPosition].restaurant.id)
                activity.startActivity(intent)
            }
        }
    }
}

