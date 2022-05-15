package com.codebros.eripple.screen.main.home.alarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentAlarmBinding
import com.codebros.eripple.screen.base.BaseFragment

class AlarmFragment : BaseFragment<AlarmViewModel, FragmentAlarmBinding>() {

    override val viewModel: AlarmViewModel by viewModels()

    override fun getViewBinding(): FragmentAlarmBinding =
        FragmentAlarmBinding.inflate(layoutInflater)

    override fun observeData() {

    }

    override fun initViews() = with(binding){
        super.initViews()
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}