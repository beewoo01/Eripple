package com.codebros.eripple.screen.main.setting.notice

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentNoticeBinding
import com.codebros.eripple.model.notice.Notice
import com.codebros.eripple.model.pointsavedhistory.PointSavedHistory
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.screen.main.history.HistoryViewModel
import com.codebros.eripple.screen.main.setting.notice.detail.NoticeDetailFragmentArgs
import com.codebros.eripple.util.provider.DefaultCustomResourcesProvider
import com.codebros.eripple.widget.adapter.ModelRecyclerAdapter
import com.codebros.eripple.widget.adapter.listener.notice.NoticeAdapterListener


class NoticeFragment : BaseFragment<NoticeViewModel, FragmentNoticeBinding>() {

    override val viewModel: NoticeViewModel by viewModels()

    override fun getViewBinding(): FragmentNoticeBinding =
        FragmentNoticeBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.noticeListLiveData.observe(this@NoticeFragment) {
            adapter.submitList(it?.toMutableList())
        }
    }

    private val resourcesProvider: DefaultCustomResourcesProvider by lazy {
        DefaultCustomResourcesProvider(requireContext())
    }

    private val adapter by lazy {
        ModelRecyclerAdapter<Notice, NoticeViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            object : NoticeAdapterListener {
                override fun onItemClick(model: Notice) {
                    Log.wtf("NoticeAdapterListener", "onItemClick")
                    findNavController().navigate(NoticeFragmentDirections.actionNoticeToNoticeDetail(model))

                }
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNotice()
    }

    override fun initViews() = with(binding) {

        noticeRecyclerview.adapter = adapter
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

    }


}