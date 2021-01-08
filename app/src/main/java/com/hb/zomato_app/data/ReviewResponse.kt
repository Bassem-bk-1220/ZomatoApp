package com.hb.zomato_app.data

import java.io.Serializable

data class ReviewResponse(val rating:Int,
                          val id:String,
                          val review_text:String,
                          val rating_text:String,
                          val review_time_friendly:String,
                          val timestamp:Long,
                          val user:UserResponse): Serializable