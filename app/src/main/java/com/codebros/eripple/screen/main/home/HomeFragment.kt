package com.codebros.eripple.screen.main.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
        viewModel.postMyCurrentPoint(1)
        viewModel.postMyBookMarkEripple(1)
        viewModel.postEvent()
    }

    @SuppressLint("SetTextI18n")
    override fun observeData() = with(binding){
        viewModel.myCurrentPoint.observe(this@HomeFragment) {
            it?.let { result ->
                currentPointTxv.text = "${result}P"

            } ?: kotlin.run {
                currentPointTxv.text = "${0}P"
            }
        }

        viewModel.myBookMarkEripple.observe(this@HomeFragment) {
            it?.let { result ->
                Log.wtf("bookmarkEripple Size", result.size.toString())
            }
        }

        viewModel.eventList.observe(this@HomeFragment) {
            it?.let { result ->
                Log.wtf("eventList Size", result.size.toString())
            }
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()

        const val TAG = "HomeFragment"
    }



}