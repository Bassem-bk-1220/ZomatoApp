package com.hb.zomato_app.data

data class ListRestaurantResponse(
    val results_shown: Int,
    val restaurants: List<RestaurantObjectResponse>
)

