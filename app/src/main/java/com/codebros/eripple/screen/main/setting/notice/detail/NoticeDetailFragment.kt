package com.codebros.eripple.screen.main.setting.notice.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentNoticeBinding
import com.codebros.eripple.databinding.FragmentNoticeDetailBinding

class NoticeDetailFragment : Fragment() {

    private var binding: FragmentNoticeDetailBinding? = null
    private val args: NoticeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoticeDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding?.run {
            val model = args.model
            titleTxv.text = model.notice_title
            contentTxv.text = model.notice_contents

            toolbar.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}