package com.codebros.eripple.screen.main.event

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codebros.eripple.databinding.EventFragmentBinding
import com.codebros.eripple.model.event.EventWithThumbnail
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.screen.main.setting.notice.NoticeFragmentDirections
import com.codebros.eripple.util.provider.DefaultCustomResourcesProvider
import com.codebros.eripple.util.recyclerview.GridLayoutDecoration
import com.codebros.eripple.widget.adapter.ModelRecyclerAdapter
import com.codebros.eripple.widget.adapter.listener.event.EventWithThumbnailListener

class EventFragment : BaseFragment<EventViewModel, EventFragmentBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEvent()
    }

    override fun initViews() {
        super.initViews()

        with(binding) {

            toolbar.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }

            recyclerview.apply {
                this.adapter = recyclerAdapter
            }
        }
    }

    private val recyclerAdapter by lazy {

        ModelRecyclerAdapter<EventWithThumbnail, EventViewModel>(
            modelList = listOf(),
            viewModel = viewModel,
            customResourcesProvider = resourcesProvider,
            object : EventWithThumbnailListener {
                override fun onClickItem(model: EventWithThumbnail) {
                    Log.wtf("EventFragment", "onClickItem")
                    findNavController().navigate(EventFragmentDirections.actionEventToEventDetail(model))
                }
            }
        )

    }

    private val resourcesProvider: DefaultCustomResourcesProvider by lazy {
        DefaultCustomResourcesProvider(requireContext())
    }

    override val viewModel: EventViewModel by viewModels()

    override fun getViewBinding(): EventFragmentBinding =
        EventFragmentBinding.inflate(layoutInflater)

    override fun observeData() {

        viewModel.eventListLiveData.observe(this@EventFragment) {
            recyclerAdapter.submitList(it.toMutableList())
        }

    }


}