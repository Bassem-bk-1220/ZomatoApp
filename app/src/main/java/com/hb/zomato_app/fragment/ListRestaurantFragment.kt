package com.hb.zomato_app.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.hb.zomato_app.R
import com.hb.zomato_app.adapters.ListRestaurantAdapter
import com.hb.zomato_app.network.ApiClient
import com.hb.zomato_app.utils.API_KEY
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.launch

class ListRestaurantFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            val listRestaurant = ApiClient.getApiService().getRestaurants(API_KEY)
            Log.e("ListRestaurantFragment", listRestaurant.toString())

            recycle_restaurant.adapter =
                ListRestaurantAdapter(listRestaurant.restaurants, requireActivity())
        }

    }
}