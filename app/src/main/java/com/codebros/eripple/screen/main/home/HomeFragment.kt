package com.codebros.eripple.screen.main.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentHomeBinding
import com.codebros.eripple.model.event.EventWithThumbnail
import com.codebros.eripple.model.event.SimpleErippleInfoWithBookmark
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.util.provider.DefaultCustomResourcesProvider
import com.codebros.eripple.widget.adapter.ModelRecyclerAdapter
import com.codebros.eripple.widget.adapter.listener.bookmark.SimpleErippleInfoWithBookmarkListener
import com.codebros.eripple.widget.adapter.listener.event.EventWithThumbnailListener

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {


    override val viewModel: HomeViewModel by viewModels()

    private val resourcesProvider: DefaultCustomResourcesProvider by lazy {
        DefaultCustomResourcesProvider(requireContext())
    }

    private val bookMarkAdapter by lazy {
        ModelRecyclerAdapter<SimpleErippleInfoWithBookmark, HomeViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            adapterListener = object : SimpleErippleInfoWithBookmarkListener {
                override fun onClickItem(model: SimpleErippleInfoWithBookmark) {
                    Log.wtf("adapter Click title", model.eripple_name)
                }
            }
        )
    }

    /*private val eventAdapter by lazy {
        ModelRecyclerAdapter<EventWithThumbnail, HomeViewModel>(
            listOf(),
            viewModel,
            customResourcesProvider = resourcesProvider,
            adapterListener = object : EventWithThumbnailListener {
                override fun onClickItem(model: EventWithThumbnail) {
                    Log.wtf("adapter Click title", model.event_title)
                }

            }
        )
    }*/

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun initViews() {
        with(binding) {
            bookmarkRecyclerView.adapter = bookMarkAdapter
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.postMyCurrentPoint(1)
        viewModel.postMyBookMarkEripple(1)
        viewModel.postEvent()
    }

    @SuppressLint("SetTextI18n")
    override fun observeData() = with(binding) {
        viewModel.myCurrentPoint.observe(this@HomeFragment) {
            it?.let { result ->
                currentPointTxv.text = "${result}P"

            } ?: kotlin.run {
                currentPointTxv.text = "${0}P"
            }
        }

        viewModel.myBookMarkEripple.observe(this@HomeFragment) {
            it?.let { result ->
                bookMarkAdapter.submitList(result.toMutableList())

            } ?: kotlin.run {
                //BookMark 한게 없거나 오류 발생
            }
        }

        viewModel.eventList.observe(this@HomeFragment) {
            it?.let { result ->
                //eventAdapter.submitList(result.toMutableList())
                Log.wtf("eventList Size", result.size.toString())
            } ?: kotlin.run {
                //Event가 없거나 오류발생
            }
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()

        const val TAG = "HomeFragment"
    }


}