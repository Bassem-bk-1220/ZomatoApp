package com.hb.zomato_app.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class OnboardingAdapter(fragmentManager: FragmentManager):FragmentPagerAdapter(fragmentManager) {

    private val fragementList= listOf(
        OnboardingFirstscreenFragment(),
        OnboardingSecondscreenFragment(),
        OnboardingThirdscreenFragment()
    )
    override fun getItem(position: Int): Fragment {
       return fragementList[position]
    }

    override fun getCount(): Int {
        return fragementList.size

    }
}