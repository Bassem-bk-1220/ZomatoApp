package com.hb.zomato_app.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.hb.zomato_app.R
import com.hb.zomato_app.data.ListRestaurantResponse
import com.hb.zomato_app.data.RestaurantObjectResponse
import com.hb.zomato_app.network.ApiClient
import com.hb.zomato_app.utils.API_KEY
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.coroutines.launch

class MapFragment : Fragment(), OnMapReadyCallback {
    private var listRestaurant = MutableLiveData<ListRestaurantResponse>()
    private var googleMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        lifecycleScope.launch {
            listRestaurant.postValue(ApiClient.getApiService().getRestaurants(API_KEY))
        }

    }

    override fun onMapReady(googleMap: GoogleMap?) {
        this.googleMap = googleMap
        listRestaurant.observe(requireActivity(), Observer { listRestaurant ->
            showRestaurantsOnTheMap(listRestaurant.restaurants)
        })


    }

    private fun showRestaurantsOnTheMap(listRestaurant: List<RestaurantObjectResponse>) {
        listRestaurants= listRestaurant as ArrayList<RestaurantObjectResponse>
        addsearchlistner()
        for (item in listRestaurant) {
            val latitude = item.restaurant.location.latitude.toDouble()
            val longitude = item.restaurant.location.longitude.toDouble()
            val latlng = LatLng(latitude, longitude)

            val marker = with(MarkerOptions()) {
                position(latlng)
                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                title(item.restaurant.name)
                snippet(item.restaurant.timings)
            }
            googleMap?.addMarker(marker)
        }
    }
    private var listRestaurants =ArrayList<RestaurantObjectResponse>()
    private fun addsearchlistner(){
        et_recherche.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                val restaurantsFiltered = ArrayList<RestaurantObjectResponse>()

                for (item in listRestaurants) {
                    if (item.restaurant.name.toLowerCase().contains(s.toString().toLowerCase())) {
                        restaurantsFiltered.add(item)
                    }
                }
                googleMap?.clear()
                for (item in restaurantsFiltered) {
                    val latitude = item.restaurant.location.latitude.toDouble()
                    val longitude = item.restaurant.location.longitude.toDouble()
                    val latlng = LatLng(latitude, longitude)

                    val marker = with(MarkerOptions()) {
                        position(latlng)
                        icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                        title(item.restaurant.name)
                        snippet(item.restaurant.timings)
                    }
                    googleMap?.addMarker(marker)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

}