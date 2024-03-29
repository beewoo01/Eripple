package com.codebros.eripple.screen.main.setting

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentSettingBinding
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.util.AccountInfoSingleton
/*import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.talk.TalkApiClient*/
import java.util.*

class SettingFragment : BaseFragment<SettingViewModel, FragmentSettingBinding>() {

    override val viewModel: SettingViewModel by viewModels()

    override fun getViewBinding(): FragmentSettingBinding =
        FragmentSettingBinding.inflate(layoutInflater)

    override fun observeData() = with(binding) {
        viewModel.accountInfo.observe(this@SettingFragment) { model ->
            nameTxv.text = model?.account_name
            phoneTxv.text =
                PhoneNumberUtils.formatNumber(model?.account_phone, Locale.getDefault().country)

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AccountInfoSingleton.account_idx?.let {
            viewModel.getAccountInfo(it)
        } ?: kotlin.run {

        }

    }

    override fun initViews() = with(binding) {

        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        noticeView.setOnClickListener {
            findNavController().navigate(R.id.action_setting_to_notice)
        }

        faqView.setOnClickListener {
            findNavController().navigate(R.id.action_setting_to_faq)
        }

        accountSettingView.setOnClickListener {
            findNavController().navigate(R.id.action_setting_to_account_setting)
        }

        bookmarkView.setOnClickListener {
            findNavController().navigate(R.id.action_setting_to_bookmark)
        }

        eventTxv.setOnClickListener {
            findNavController().navigate(R.id.action_setting_to_event)
        }

        inquiryPhone.setOnClickListener {
            makingPhoneCall()
        }

        inquiryKakao.setOnClickListener {
            initKakaoPlusChannel()
        }
    }

    private fun makingPhoneCall() {
        startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:051-265-4470")) )
    }

    private fun initKakaoPlusChannel(){

        /*val url = TalkApiClient.instance.channelChatUrl(getString(R.string.kakaoChatId))
        KakaoCustomTabsClient.open(requireContext(), url)*/

    }
}