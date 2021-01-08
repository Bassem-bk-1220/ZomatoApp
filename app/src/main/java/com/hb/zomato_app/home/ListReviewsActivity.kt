package com.hb.zomato_app.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hb.zomato_app.R
import com.hb.zomato_app.adapters.ListReviewAdapter
import com.hb.zomato_app.data.ListReviewsResponse
import com.hb.zomato_app.utils.LIST_REVIEW
import kotlinx.android.synthetic.main.activity_list_review.*


class ListReviewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_review)
        supportActionBar?.hide()
        img_details_review_back.setOnClickListener { onBackPressed() }

        val listReview  = intent?.extras?.getSerializable(LIST_REVIEW) as ListReviewsResponse?
        listReview?.let {
            recycle_reviews.adapter  =ListReviewAdapter(listReview.user_reviews,this)
        }
    }

}