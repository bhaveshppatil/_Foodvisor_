package com.masai.foodvisor.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.masai.foodvisor.fragments.bottomSheetFragment.FavoriteFragment
import com.masai.foodvisor.fragments.bottomSheetFragment.SearchFragment

internal class ViewPagerAdapter(
    context: Context,
    fragmentManager: FragmentManager,
    var totalTabs: Int
) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                SearchFragment()
            }
            1 -> {
                com.masai.foodvisor.fragments.bottomSheetFragment.AddFoodFragmentBtm()
            }
            2 -> {
                FavoriteFragment()
            }
            else -> getItem(position)
        }
    }
}

