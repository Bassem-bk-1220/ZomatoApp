package com.hb.zomato_app.data

import java.io.Serializable

data class ListReviewsResponse(
    val reviews_count: Int,
    val user_reviews: List<ReviewObjectResponse>
) : Serializable