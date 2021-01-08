package com.hb.zomato_app.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hb.zomato_app.R
import com.hb.zomato_app.adapters.ListReviewAdapter
import com.hb.zomato_app.data.ListReviewsResponse
import com.hb.zomato_app.data.RestaurantResponse
import com.hb.zomato_app.data.ReviewObjectResponse
import com.hb.zomato_app.network.ApiClient
import com.hb.zomato_app.utils.*
import kotlinx.android.synthetic.main.activity_details_restaurants.*
import kotlinx.coroutines.launch
import java.lang.reflect.Type


class DetailsRestaurantsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_restaurants)
        supportActionBar?.hide()
        img_details_back.setOnClickListener { onBackPressed() }

        img_add_review.setOnClickListener { goToAddReviewActivity() }
        val idRestaurant: String? = intent?.extras?.getString(RESTAURANT_ID)
        idRestaurant?.let {
            // get web service details restaurant


            lifecycleScope.launch {
                val detailsRestaurant: RestaurantResponse =
                    ApiClient.getApiService()
                        .getDetailsRestaurant(API_KEY, idRestaurant.toInt())

                tv_details_name.text = detailsRestaurant.name
                if (detailsRestaurant.thumb.isEmpty()) {
                    img_details_restaurant.load(R.drawable.placeholder)
                } else {
                    img_details_restaurant.load(detailsRestaurant.thumb)
                }

                tv_address.text = detailsRestaurant.location.address
                rating_bar_stars.rating = detailsRestaurant.user_rating.aggregate_rating.toFloat()
                tv_open.text = detailsRestaurant.timings

                img_details_call.setOnClickListener {
                    val intent = Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse("tel:" + Uri.encode(detailsRestaurant.phone_number))
                    )
                    startActivity(intent)
                }
                img_details_localite.setOnClickListener {
                    val gmmIntentUri =
                        Uri.parse("geo:${detailsRestaurant.location.latitude},${detailsRestaurant.location.longitude}")
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    startActivity(mapIntent)
                }

                img_favorite.setOnClickListener {
                    saveListRestaurant(detailsRestaurant)
                }

            }



            lifecycleScope.launch {
                val listReview: ListReviewsResponse =
                    ApiClient.getApiService().getReviews(API_KEY, idRestaurant.toInt())
                val listReviewToAdapter = ArrayList<ReviewObjectResponse>()
                if (listReview.user_reviews.size > 2) {
                    listReviewToAdapter.add(listReview.user_reviews[0])
                    listReviewToAdapter.add(listReview.user_reviews[1])
                } else {
                    listReviewToAdapter.addAll(listReview.user_reviews)
                }
                recycle_reviews.adapter =
                    ListReviewAdapter(listReviewToAdapter, this@DetailsRestaurantsActivity)

                tv_link_details_review.setOnClickListener {
                    goToListReviews(listReview)
                }

                img_details_next.setOnClickListener {
                    goToListReviews(listReview)
                }

            }


        } ?: kotlin.run {
            Toast.makeText(this, "id restaurant is null", Toast.LENGTH_LONG).show()
        }


    }

    private fun goToAddReviewActivity() {
        startActivity(Intent(this, AddReviewActivity::class.java))
    }

    private fun saveListRestaurant(detailsRestaurant: RestaurantResponse) {
        val listRestaurants = getRestaurantsListFromSharedPrefs()
        var isRestaurantExist = false
        for (item in listRestaurants) {
            if (item.id == detailsRestaurant.id) {
                isRestaurantExist = true
            }
        }

        if (!isRestaurantExist) {
            listRestaurants.add(detailsRestaurant)
            val preference: SharedPreferences =
                getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE)
            preference.edit().putString(RESTAURANT_DETAILS_LIST, Gson().toJson(listRestaurants))
                .apply()

            Toast.makeText(
                this,
                "Restaurant is saved , you can check your favorites later",
                Toast.LENGTH_LONG
            ).show()
        } else {
            Toast.makeText(
                this,
                "Restaurant is Already in favorites , you can check your favorites later",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    private fun getRestaurantsListFromSharedPrefs(): ArrayList<RestaurantResponse> {
        val prefs: SharedPreferences = getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(RESTAURANT_DETAILS_LIST, null)
        if (json == null) return ArrayList()
        val type: Type = object : TypeToken<ArrayList<RestaurantResponse>>() {}.type
        return Gson().fromJson(json, type)
    }

    private fun goToListReviews(listReview: ListReviewsResponse) {
        val intent = Intent(this@DetailsRestaurantsActivity, ListReviewsActivity::class.java)
        intent.putExtra(LIST_REVIEW, listReview)
        startActivity(intent)
    }

}