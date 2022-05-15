package com.codebros.eripple.screen.main.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentBookMarkBinding
import com.codebros.eripple.model.event.SimpleErippleInfoWithBookmark
import com.codebros.eripple.model.exchange.AccountExchangeHistory
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.screen.main.my_point.exchange.ExchangeHistoryViewModel
import com.codebros.eripple.util.AccountInfoSingleton
import com.codebros.eripple.util.provider.DefaultCustomResourcesProvider
import com.codebros.eripple.widget.adapter.ModelRecyclerAdapter
import com.codebros.eripple.widget.adapter.listener.bookmark.SimpleErippleInfoWithBookmarkListener

class BookMarkFragment : BaseFragment<BookMarkViewModel, FragmentBookMarkBinding>() {

    override val viewModel: BookMarkViewModel by viewModels()

    override fun getViewBinding(): FragmentBookMarkBinding =
        FragmentBookMarkBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.myBookMarkEripple.observe(this) {
            it?.let { result ->
                bookMarkAdapter.submitList(result.toMutableList())
            }
        }
    }

    private val resourcesProvider: DefaultCustomResourcesProvider by lazy {
        DefaultCustomResourcesProvider(requireContext())
    }

    private val bookMarkAdapter by lazy {
        ModelRecyclerAdapter<SimpleErippleInfoWithBookmark, BookMarkViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            adapterListener = object : SimpleErippleInfoWithBookmarkListener {
                override fun onClickItem(model: SimpleErippleInfoWithBookmark) {
                    Log.wtf("adapter Click title", model.eripple_name)
                }

                override fun onHeartClick(model: SimpleErippleInfoWithBookmark) {
                    Log.wtf("adapter onHeartClick Click title", model.eripple_name)
                }
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AccountInfoSingleton.account_idx?.let { viewModel.postMyBookMarkEripple(it) }
    }

    override fun initViews() = with(binding) {
        super.initViews()

        bookmarkRecyclerView.adapter = bookMarkAdapter

        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

    }

}