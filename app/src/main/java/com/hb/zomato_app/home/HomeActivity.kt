package com.hb.zomato_app.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.hb.zomato_app.R
import com.hb.zomato_app.fragment.FavoriteFragment
import com.hb.zomato_app.fragment.ListRestaurantFragment
import com.hb.zomato_app.fragment.MapFragment
import com.hb.zomato_app.fragment.SearchFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.view.*


class HomeActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        image_icon_list.setOnClickListener(this)
        image_icon_search.setOnClickListener(this)
        image_icon_map.setOnClickListener(this)
        image_icon_favorite.setOnClickListener(this)

        image_icon_list.setColorFilter(ContextCompat.getColor(this, R.color.colorYellow))
        showFragment(ListRestaurantFragment())

    }


    override fun onClick(v: View?) {
        when (v) {
            image_icon_list -> {
                setColorMenuBlack()
                image_icon_list.setColorFilter(ContextCompat.getColor(this, R.color.colorYellow))
                showFragment(ListRestaurantFragment())
            }
            image_icon_search -> {
                setColorMenuBlack()
                image_icon_search.setColorFilter(ContextCompat.getColor(this, R.color.colorYellow))
                showFragment(SearchFragment())
            }
            image_icon_map -> {
                setColorMenuBlack()
                image_icon_map.setColorFilter(ContextCompat.getColor(this, R.color.colorYellow))
                showFragment(MapFragment())
            }
            image_icon_favorite -> {
                setColorMenuBlack()
                image_icon_favorite.setColorFilter(ContextCompat.getColor(this, R.color.colorYellow))
                showFragment(FavoriteFragment())
            }
        }
    }

    private fun setColorMenuBlack() {
        image_icon_list.setColorFilter(ContextCompat.getColor(this, R.color.colorBlack))
        image_icon_search.setColorFilter(ContextCompat.getColor(this, R.color.colorBlack))
        image_icon_map.setColorFilter(ContextCompat.getColor(this, R.color.colorBlack))
        image_icon_favorite.setColorFilter(ContextCompat.getColor(this, R.color.colorBlack))
    }
    private  fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_menu,fragment,"").commit()
    }

}