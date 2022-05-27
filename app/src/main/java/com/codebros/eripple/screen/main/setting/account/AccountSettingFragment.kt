package com.codebros.eripple.screen.main.setting.account

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentAccountSettingBinding
import com.codebros.eripple.screen.account.login.LoginActivity
import com.codebros.eripple.screen.main.setting.account.outaccout.OutAccountFragment


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
            findNavController().navigate(R.id.action_account_setting_to_changePsw)
        }

        bankEditTxv.setOnClickListener {
            findNavController().navigate(R.id.action_account_setting_to_bank_edit)
        }

        logoutTxv.setOnClickListener {
            setAutoLogin()
            requireActivity().run {
                startActivity(Intent(requireActivity(), LoginActivity::class.java))
                finish()
            }

        }

        outAccountTxv.setOnClickListener {
            findNavController().navigate(R.id.action_account_setting_to_outaccount_fragment)
        }

    }

    private fun setAutoLogin() {
        val pref = requireActivity().getSharedPreferences(LoginActivity.PREFER_NAME, LoginActivity.PREFERENCE_MODE)
        val email = pref.getString(LoginActivity.PREFER_ID, null)
        val pw = pref.getString(LoginActivity.PREFER_PW, null)

        if(!email.isNullOrEmpty() || !pw.isNullOrEmpty()) {
            val editor = pref.edit()
            editor.clear()
            editor.apply()
        }
    }


}