package com.hb.zomato_app.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hb.zomato_app.R
import com.hb.zomato_app.adapters.ListRestaurantFavoritesAdapter
import com.hb.zomato_app.data.RestaurantResponse
import com.hb.zomato_app.utils.PACKAGE_NAME
import com.hb.zomato_app.utils.RESTAURANT_DETAILS_LIST
import kotlinx.android.synthetic.main.fragment_favorite.*
import java.lang.reflect.Type

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycle_restaurant_favorite.adapter =
            ListRestaurantFavoritesAdapter(getRestaurantsListFromSharedPrefs(), requireActivity())
    }


    private fun getRestaurantsListFromSharedPrefs(): List<RestaurantResponse> {
        val prefs: SharedPreferences =
            requireActivity().getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(RESTAURANT_DETAILS_LIST, null)
        if (json == null) {
            tv_no_favorites.visibility = View.VISIBLE
            return emptyList()
        }
        val type: Type = object : TypeToken<List<RestaurantResponse>>() {}.type
        return Gson().fromJson(json, type)
    }

}