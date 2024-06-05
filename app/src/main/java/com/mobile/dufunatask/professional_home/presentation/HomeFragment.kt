package com.mobile.dufunatask.professional_home.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import com.mobile.dufunatask.R
import com.mobile.dufunatask.databinding.FragmentHomeBinding
import com.mobile.dufunatask.professional_home.presentation.component.HomeViewPagerAdapter
import com.mobile.dufunatask.utils.hide
import com.mobile.dufunatask.utils.invisible
import com.mobile.dufunatask.utils.show
import com.mobile.dufunatask.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val homeViewModel: HomeViewModel by viewModels()
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val homeViewPagerAdapter by lazy { HomeViewPagerAdapter(requireActivity()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpObservers()
    }

    private fun setUpViews() = with(binding) {
        setUpViewPager()
        clockIn.clockedIn {  }
        clockOut.clockedOut {  }
        takeABreak.clockedOut {  }
    }

    private fun setUpObservers() = with(homeViewModel){
        viewLifecycleOwner.lifecycleScope.launch {
            username.collectLatest {
                setUsername(it)
            }
        }
    }

    private fun setUpViewPager() = with(binding){
        categoriesViewPager.adapter = homeViewPagerAdapter
        TabLayoutMediator(homeCategoriesTabLayout, categoriesViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.medication)
                1 -> tab.text = getString(R.string.activities)
            }
        }.attach()
    }

    private fun setUsername(name: String) = with(binding){
        this.name.text = getString(R.string.hi, name)
    }

    private fun Button.clockedIn(otherAction: () -> Unit) = with(binding){
        setOnClickListener {
            clockedIn.show()
            clockOutGroup.show()
            this@clockedIn.invisible()
            otherAction()
        }
    }

    private fun Button.clockedOut(otherAction: () -> Unit) = with(binding){
        setOnClickListener {
            clockedIn.hide()
            clockOutGroup.hide()
            clockIn.show()
            otherAction()
        }
    }
}