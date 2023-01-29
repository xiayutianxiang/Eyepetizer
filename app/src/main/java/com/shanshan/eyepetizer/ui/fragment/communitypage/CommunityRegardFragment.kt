package com.shanshan.eyepetizer.ui.fragment.communitypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shanshan.eyepetizer.R

class CommunityRegardFragment : Fragment() {

    companion object {
        fun getInstance() = CommunityRegardFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comm, container, false)
    }
}