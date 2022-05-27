package com.codebros.eripple.screen.main.setting.account.outaccout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentOutAccountBinding
import com.codebros.eripple.model.account.AccountInfo
import com.codebros.eripple.screen.account.login.LoginActivity
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.util.AccountInfoSingleton


class OutAccountFragment : BaseFragment<OutAccountViewModel, FragmentOutAccountBinding>() {

    override val viewModel: OutAccountViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun getViewBinding(): FragmentOutAccountBinding =
        FragmentOutAccountBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.outAccountState.observe(this@OutAccountFragment) {
            it?.let { result ->
                if (result == 1) {
                    Toast.makeText(requireContext(), "회원에서 탈퇴하였습니다.", Toast.LENGTH_SHORT).show()
                    AccountInfoSingleton.account_idx = null
                    setAutoLogin()
                    startActivity(Intent(requireActivity(), LoginActivity::class.java))
                    requireActivity().finish()

                } else  {
                    Toast.makeText(requireContext(), "계정정보가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }

            }
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

    override fun initViews() = with(binding) {
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        accountOutBtn.setOnClickListener {
            Log.wtf("accountOutBtn", "accountOutBtn")
            outAccount()
        }

    }

    private fun outAccount() = with(binding) {

        OutAccountDialog(requireContext(), callBack = {
            if (it) {

                if (binding.nameEdit.text.toString().isNotEmpty() &&
                    binding.passwordEdit.text.toString().isNotEmpty() &&
                    binding.phoneEdit.text.toString().isNotEmpty()
                ) {
                    AccountInfoSingleton.account_idx?.let {
                        viewModel.outAccount(
                            it,
                            phoneEdit.text.toString(),
                            passwordEdit.text.toString(),
                            nameEdit.text.toString(),
                        )


                    } ?: kotlin.run {
                        Toast.makeText(requireContext(), "회원정보를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                        requireActivity().finish()
                    }


                } else {
                    Toast.makeText(requireContext(), "회원정보를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                }

            } else {

                requireActivity().onBackPressed()

            }
        }).show()



    }


}