package com.hb.zomato_app.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hb.zomato_app.R
import kotlinx.android.synthetic.main.activity_add_review.*


class AddReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_review)


        btn_submit_review.setOnClickListener {
            val message = et_review.editableText.toString()
            if (message.isEmpty()) {
                Toast.makeText(
                    this,
                    "please enter your review before submitting",
                    Toast.LENGTH_LONG
                ).show()
            } else {
//                lifecycleScope.launch {
//                    ApiClient.getApiService().postNewReview(API_KEY, 121, message)
//                }
                Toast.makeText(
                    this,
                    "Review Was sent successfully",
                    Toast.LENGTH_LONG
                ).show()
                finish()

            }
        }

    }

}