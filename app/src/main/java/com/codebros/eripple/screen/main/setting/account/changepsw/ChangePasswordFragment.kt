package com.codebros.eripple.screen.main.setting.account.changepsw

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.codebros.eripple.databinding.FragmentChangePasswordBinding
import com.codebros.eripple.screen.account.chagepsw.ChangePasswordViewModel
import com.codebros.eripple.screen.base.BaseFragment

class ChangePasswordFragment
    : BaseFragment<ChangePasswordFragmentViewModel, FragmentChangePasswordBinding>() {

    override val viewModel: ChangePasswordFragmentViewModel by viewModels()


    override fun getViewBinding(): FragmentChangePasswordBinding =
        FragmentChangePasswordBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.changePasswordLiveData.observe(this@ChangePasswordFragment) {
            if (it > 0) {
                // TODO: ShowPopUp Success Change Password
                showToast("비밀번호가 변경되었습니다.")
            } else {
                showToast("비밀번호 변경에 실패하였습니다.")
            }
        }
    }

    override fun initViews() = with(binding) {
        super.initViews()

        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        nextBtn.setOnClickListener {
            available()
        }
    }

    private fun available() = with(binding) {
        if (passwordEdt.text.toString().isEmpty() || passwordEdt.text.toString().length < 8) {
            showToast("비밀번호를 8자 이상 등록해주세요.")
        } else if (confirmPasswordEdt.text.toString() != passwordEdt.text.toString()) {
            showToast("비밀번호가 일치하지 않습니다.")
        } else {
            viewModel.changePassword(1, passwordEdt.text.toString())
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }


}