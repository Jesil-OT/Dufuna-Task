package com.mobile.dufunatask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobile.dufunatask.databinding.ActivityMainBinding
import com.mobile.dufunatask.utils.hide
import com.mobile.dufunatask.utils.show
import com.mobile.dufunatask.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.caresaas_nav_host) as NavHostFragment
    }
    private val navController: NavController by lazy {
        navHostFragment.navController
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(binding.root)
        binding.caresaasBottomNavigation.reSelect()
    }


    private fun BottomNavigationView.reSelect() = with(this) {
        setupWithNavController(navController)
        setOnItemReselectedListener { }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> showWithTransition()
                else -> hideWithTransition()
            }
        }

    }

    private fun BottomNavigationView.showWithTransition() {
        TransitionManager.beginDelayedTransition(
            binding.root,
            Slide(Gravity.BOTTOM).excludeTarget(R.id.caresaas_nav_host, true)
        )
        show()
    }

    private fun BottomNavigationView.hideWithTransition() = with(this) {
        TransitionManager.beginDelayedTransition(
            binding.root,
            Slide(Gravity.BOTTOM).excludeTarget(R.id.caresaas_nav_host, true)
        )
        hide()
    }

}