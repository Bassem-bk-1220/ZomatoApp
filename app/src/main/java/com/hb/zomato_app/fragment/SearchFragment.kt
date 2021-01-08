package com.hb.zomato_app.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.hb.zomato_app.R
import com.hb.zomato_app.adapters.ListRestaurantAdapter
import com.hb.zomato_app.data.ListRestaurantResponse
import com.hb.zomato_app.data.RestaurantObjectResponse
import com.hb.zomato_app.network.ApiClient
import com.hb.zomato_app.utils.API_KEY
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var listRestaurant: ListRestaurantResponse

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            listRestaurant = ApiClient.getApiService().getRestaurants(API_KEY)
            setSearchRecyclerView(listRestaurant.restaurants)
            progress_bar_search.visibility = View.GONE
            filterListRestaurants(listRestaurant)
        }


    }


    private fun filterListRestaurants(listRestaurant: ListRestaurantResponse) {
        et_search.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(text: Editable) {
                val restaurantsFiltered = ArrayList<RestaurantObjectResponse>()

                for (item in listRestaurant.restaurants) {
                    if (item.restaurant.name.toLowerCase().contains(text.toString().toLowerCase())) {
                        restaurantsFiltered.add(item)
                    }
                }
                setSearchRecyclerView(restaurantsFiltered)

            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        })

    }

    private fun setSearchRecyclerView(listRestaurants: List<RestaurantObjectResponse>) {
        if (listRestaurants.isEmpty()){
            tv_no_results.visibility = View.VISIBLE
        }else{
            tv_no_results.visibility = View.GONE
        }
        recycle_search_restaurant.adapter =
            ListRestaurantAdapter(
                listRestaurants,
                requireActivity(),
                R.layout.item_restaurant_search
            )
    }

}