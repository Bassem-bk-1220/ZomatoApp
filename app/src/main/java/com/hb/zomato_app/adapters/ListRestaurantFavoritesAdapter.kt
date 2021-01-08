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
import com.hb.zomato_app.data.RestaurantResponse
import com.hb.zomato_app.home.DetailsRestaurantsActivity
import com.hb.zomato_app.utils.RESTAURANT_ID
import kotlinx.android.synthetic.main.item_restaurant.view.*
import me.zhanghai.android.materialratingbar.MaterialRatingBar

class ListRestaurantFavoritesAdapter
    (
    private val listRestaurant: List<RestaurantResponse>,
    private val activity: Activity
) :
    RecyclerView.Adapter<ListRestaurantFavoritesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_restaurant,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.stars.isEnabled = false
        val numStars = listRestaurant[position].user_rating.aggregate_rating.toFloat()

        holder.stars.rating = numStars
        holder.tvTitle.text = listRestaurant[position].name
        holder.tvOpen.text = listRestaurant[position].timings

        if (listRestaurant[position].thumb.isEmpty()) {
            holder.imgrestaurant.load(R.drawable.placeholder)
        } else {
            holder.imgrestaurant.load(listRestaurant[position].thumb)
        }
    }

    override fun getItemCount(): Int {
        return listRestaurant.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tv_title
        val tvOpen: TextView = view.tv_open
        val tvDistance: TextView = view.tv_distance
        val imgrestaurant: ImageView = view.img_restaurant
        val stars: MaterialRatingBar = view.stars

        init {
            itemView.setOnClickListener {
                val intent = Intent(activity, DetailsRestaurantsActivity::class.java)
                intent.putExtra(RESTAURANT_ID,listRestaurant[adapterPosition].id)
                activity.startActivity(intent)
            }
        }
    }
}

