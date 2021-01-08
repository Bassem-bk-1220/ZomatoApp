package com.hb.zomato_app.network

import com.hb.zomato_app.data.ListRestaurantResponse
import com.hb.zomato_app.data.ListReviewsResponse
import com.hb.zomato_app.data.RestaurantResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServices {
    @GET("search")
    suspend fun getRestaurants(@Header("X-Zomato-API-Key") key: String): ListRestaurantResponse

    @GET("reviews")
    suspend fun getReviews(
        @Header("X-Zomato-API-Key") key: String,
        @Query("res_id") id: Int
    ): ListReviewsResponse

    @GET("restaurant")
    suspend fun getDetailsRestaurant(
        @Header("X-Zomato-API-Key") key: String,
        @Query("res_id") id: Int
    ): RestaurantResponse

    @POST("/review")
    suspend fun postNewReview(
        @Header("X-Zomato-API-Key") key: String,
        @Query("res_id") id: Int,
        @Query("review_text") message: String
    ): Boolean

}