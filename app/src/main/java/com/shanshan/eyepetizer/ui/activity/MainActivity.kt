package com.shanshan.eyepetizer.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {

        //val controller =  Navigation.findNavController(this, R.id.my_nav_host_fragment)

        //使用FragmentContainerView，先将对应视图强转为NavHostFragment，通过这个去拿到NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        val controller = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigation, controller)
    }
}