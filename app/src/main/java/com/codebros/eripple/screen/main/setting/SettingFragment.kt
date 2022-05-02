package com.codebros.eripple.screen.main.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentSettingBinding
import com.codebros.eripple.screen.base.BaseFragment

class SettingFragment : BaseFragment<SettingViewModel, FragmentSettingBinding>() {

    override val viewModel: SettingViewModel by viewModels()

    override fun getViewBinding(): FragmentSettingBinding =
        FragmentSettingBinding.inflate(layoutInflater)

    override fun observeData() {
        
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}