package com.codebros.eripple.screen.main.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentHomeBinding
import com.codebros.eripple.model.event.EventWithThumbnail
import com.codebros.eripple.model.event.SimpleErippleInfoWithBookmark
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.screen.main.MainActivity
import com.codebros.eripple.screen.main.setting.notice.NoticeFragmentDirections
import com.codebros.eripple.util.AccountInfoSingleton
import com.codebros.eripple.util.provider.DefaultCustomResourcesProvider
import com.codebros.eripple.widget.adapter.ModelRecyclerAdapter
import com.codebros.eripple.widget.adapter.listener.bookmark.SimpleErippleInfoWithBookmarkListener
import com.codebros.eripple.widget.adapter.listener.event.EventWithThumbnailListener
import com.codebros.eripple.widget.adapter.viewpager.EventViewPagerInHomeFrag
import com.google.android.material.tabs.TabLayoutMediator

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
                    /*val action = HomeFragmentDirections.actionHomeToErippleInfo(model.eripple_idx)*/

                    /*findNavController().navigate(R.id.eripple_info,
                        HomeFragmentDirections.actionHomeToErippleInfo(model.eripple_idx)
                    })*/

//                    findNavController().navigate(HomeFragmentDirections.actionHomeToErippleInfo(model.eripple_idx))
                    (requireActivity() as MainActivity).setNavigate(MainActivity.ERIPPLE_INFO, param = model.eripple_idx)
                    //findNavController().navigate(NoticeFragmentDirections.actionNoticeToNoticeDetail(model))
                }


                override fun onHeartClick(model: SimpleErippleInfoWithBookmark) {
                    Log.wtf("adapter onHeartClick Click title", model.eripple_name)
                }
            }
        )
    }


    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun initViews() {
        with(binding) {
            bookmarkRecyclerView.adapter = bookMarkAdapter

            allPointContainer.setOnClickListener {
                (requireActivity() as MainActivity).setNavigate(MainActivity.MY_POINT)
                //findNavController().navigate(R.id.my_point)
            }

            moreBookmarkTxv.setOnClickListener {
                //findNavController().navigate(R.id.action_home_to_bookmark)
                (requireActivity() as MainActivity).setNavigate(MainActivity.BOOKMARK)
            }

            alarmImb.setOnClickListener {
                (requireActivity() as MainActivity).setNavigate(MainActivity.BOOKMARK)
            }

        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.wtf("Home onViewCreated", "${AccountInfoSingleton.account_idx}")
        AccountInfoSingleton.account_idx?.let {
            viewModel.postMyCurrentPoint(it)
            viewModel.postMyBookMarkEripple(it)
            viewModel.getAlarmList(it)
        } ?: run {
            Log.wtf("Home onViewCreated","accountIdx is null")
        }

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
                if (result.isNotEmpty()) {
                    moreBookmarkTxv.isVisible = true
                    bookmarkRecyclerView.isVisible = true
                    haveNoBookmarkTxv.visibility = View.INVISIBLE
                } else {
                    moreBookmarkTxv.isVisible = false
                    bookmarkRecyclerView.visibility = View.INVISIBLE
                    haveNoBookmarkTxv.visibility = View.VISIBLE
                }

            } ?: kotlin.run {
                moreBookmarkTxv.isVisible = false
                bookmarkRecyclerView.visibility = View.INVISIBLE
                haveNoBookmarkTxv.visibility = View.VISIBLE
                //BookMark 한게 없거나 오류 발생
            }
        }

        viewModel.eventList.observe(this@HomeFragment) {
            it?.let { result ->

                eventViewpager.adapter =
                    EventViewPagerInHomeFrag(
                        result.toMutableList(),
                        listener = object : EventWithThumbnailListener {
                            override fun onClickItem(model: EventWithThumbnail) {
                                Log.wtf("onClickItem", "onClickItem")
                            }

                        })

                TabLayoutMediator(tabLayout, eventViewpager)
                { tab, _ ->
                    tab.view.isClickable = false
                }.attach()

            } ?: kotlin.run {
                Log.wtf("eventList Size", "isNull")
                //Event가 없거나 오류발생
            }
        }

        viewModel.alarmList.observe(this@HomeFragment) {
            it?.let { alramList ->
                with(binding.alarmCountTxv) {
                    if (alramList.count() > 0) {
                        isVisible = true
                        text = alramList.size.toString()
                    } else {
                        isVisible = false
                    }
                }
            }
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()

        const val TAG = "HomeFragment"
    }


}