package com.hb.zomato_app.data

data class RestaurantResponse(
    val name: String,
    val id: String,
    val location: LocationResponse,
    val timings: String,
    val thumb: String,
    val user_rating:RatingResponse,
    val phone_number:String
)
