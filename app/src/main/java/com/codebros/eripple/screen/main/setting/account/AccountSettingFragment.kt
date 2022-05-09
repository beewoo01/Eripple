package com.codebros.eripple.screen.main.setting.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentAccountSettingBinding


class AccountSettingFragment : Fragment() {

    private val binding by lazy {
        FragmentAccountSettingBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        changePswTxv.setOnClickListener {

        }

        bankEditTxv.setOnClickListener {

        }

        logoutTxv.setOnClickListener {

        }

        outAccountTxv.setOnClickListener {

        }

    }


}