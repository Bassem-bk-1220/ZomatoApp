package com.hb.zomato_app.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.hb.zomato_app.R
import com.hb.zomato_app.home.HomeActivity
import com.hb.zomato_app.onboarding.OnboardingActivity
import com.hb.zomato_app.utils.IS_ONBOARDING_SHOWING
import com.hb.zomato_app.utils.PACKAGE_NAME

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val preference: SharedPreferences =
            getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE)
        val isOnboardingShowing = preference.getBoolean(IS_ONBOARDING_SHOWING, false)

        Handler().postDelayed({
            if (isOnboardingShowing) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, OnboardingActivity::class.java))
                finish()
            }
        }, 2000)

    }
}