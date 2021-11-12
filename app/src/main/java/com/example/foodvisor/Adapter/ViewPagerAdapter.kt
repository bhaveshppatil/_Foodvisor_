package com.example.foodvisor.Adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.foodvisor.Fragments.BottomSheetFragment.FavoriteFragment
import com.example.foodvisor.Fragments.BottomSheetFragment.AddFoodFragmentBtm
import com.example.foodvisor.Fragments.BottomSheetFragment.SearchFragment

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
                AddFoodFragmentBtm()
            }
            2 -> {
                FavoriteFragment()
            }
            else -> getItem(position)
        }
    }
}

