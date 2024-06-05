package com.mobile.dufunatask.professional_home.presentation.component

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mobile.dufunatask.professional_home.presentation.categories.activities.ActivitiesFragment
import com.mobile.dufunatask.professional_home.presentation.categories.medication.MedicationFragment

class HomeViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity){
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> MedicationFragment()
                1 -> ActivitiesFragment()
                else -> MedicationFragment()
            }
        }

        override fun getItemCount(): Int = 2

}