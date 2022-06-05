package com.shanshan.eyepetizer.ui.fragment.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.base.BaseFragment
import com.shanshan.eyepetizer.databinding.FragmentDicoveryBinding

class DiscoveryFragment : BaseFragment<FragmentDicoveryBinding>() {

    companion object {
        const val TAG = "DiscoveryFragment"
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_dicovery
    }
}