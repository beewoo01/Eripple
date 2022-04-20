package com.codebros.eripple.screen.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentHomeBinding
import com.codebros.eripple.screen.base.BaseFragment

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {


    override val viewModel: HomeViewModel by viewModels()

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun observeData() {

    }


    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()

        const val TAG = "HomeFragment"
    }



}