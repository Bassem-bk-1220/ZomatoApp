package com.hb.zomato_app.onboarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.hb.zomato_app.*
import com.hb.zomato_app.home.HomeActivity
import com.hb.zomato_app.utils.IS_ONBOARDING_SHOWING
import com.hb.zomato_app.utils.PACKAGE_NAME
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import kotlinx.android.synthetic.main.activity_main.*

class OnboardingActivity : AppCompatActivity() {
    private lateinit var viewModel: OnboardingActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(OnboardingActivityVM::class.java)

        view_pager_Obourding.adapter =
            OnboardingAdapter(supportFragmentManager)
        indicator_view
            .setSlideMode(IndicatorSlideMode.WORM)
            .setIndicatorStyle(IndicatorStyle.CIRCLE)
            .setupWithViewPager(view_pager_Obourding)

        button_skip.setOnClickListener {
            saveOnboarding()
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun saveOnboarding() {
        val preference: SharedPreferences =
            getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE)
        preference.edit().putBoolean(IS_ONBOARDING_SHOWING, true).apply()
    }
}