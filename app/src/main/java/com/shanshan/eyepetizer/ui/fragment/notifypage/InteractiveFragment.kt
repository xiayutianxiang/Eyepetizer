package com.shanshan.eyepetizer.ui.fragment.notifypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shanshan.eyepetizer.R

/**
 * A simple [Fragment] subclass.
 * Use the [InteractiveFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InteractiveFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_interactive, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = InteractiveFragment()
    }
}