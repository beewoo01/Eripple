package com.codebros.eripple.screen.main.my_point

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codebros.eripple.R

class MyPointFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_point, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) = MyPointFragment()

        const val TAG = "MyPointFragment"
    }
}