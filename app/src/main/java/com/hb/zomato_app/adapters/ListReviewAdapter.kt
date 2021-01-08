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
import com.hb.zomato_app.data.ReviewObjectResponse
import com.hb.zomato_app.home.DetailsRestaurantsActivity
import com.hb.zomato_app.utils.RESTAURANT_ID
import kotlinx.android.synthetic.main.item_restaurant.view.stars
import kotlinx.android.synthetic.main.item_review.view.*
import me.zhanghai.android.materialratingbar.MaterialRatingBar

class ListReviewAdapter
    (
    private val listReview: List<ReviewObjectResponse>,
    private val activity: Activity
) :
    RecyclerView.Adapter<ListReviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_review,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.starsreviews.isEnabled = false
        val numStars = listReview[position].review.rating.toFloat()
        holder.starsreviews.rating = numStars
        holder.tvname.text = listReview[position].review.user.name
        holder.tvmessage.text = listReview[position].review.review_text
        holder.tvdate.text = listReview[position].review.review_time_friendly
        holder.tvnumber.text = listReview[position].review.rating_text

        if (listReview[position].review.user.profile_url.isEmpty()) {
            holder.imglogo.load(R.drawable.placeholder)
        } else {
            holder.imglogo.load(listReview[position].review.user.profile_image)

        }
    }

    override fun getItemCount(): Int {
        return listReview.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvname: TextView = view.tv_name
        val tvnumber: TextView = view.tv_number
        val tvdate: TextView = view.tv_date
        val tvmessage: TextView = view.tv_message
        val imglogo: ImageView = view.img_logo
        val starsreviews: MaterialRatingBar = view.stars

    }
}

