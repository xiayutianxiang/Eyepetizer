package com.shanshan.eyepetizer.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.navigation.Navigation
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
        initData()
    }

    private fun initView() {
        val controller =  Navigation.findNavController(this, R.id.my_nav_host_fragment)
        NavigationUI.setupWithNavController(binding.bottomNavigation,controller)
    }

    private fun initData() {

    }
}