package com.shanshan.eyepetizer.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.shanshan.eyepetizer.R

abstract class BaseFragment<V : ViewDataBinding> : Fragment() {

    lateinit var binding: V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater,getLayoutId(),container,false)
        return binding.root
    }

    abstract fun getLayoutId(): Int
}