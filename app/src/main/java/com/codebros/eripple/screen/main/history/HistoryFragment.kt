package com.codebros.eripple.screen.main.history

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentHistoryBinding
import com.codebros.eripple.model.pointsavedhistory.PointSavedHistory
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.util.provider.DefaultCustomResourcesProvider
import com.codebros.eripple.widget.adapter.ModelRecyclerAdapter

class HistoryFragment : BaseFragment<HistoryViewModel, FragmentHistoryBinding>() {


    override val viewModel: HistoryViewModel by viewModels()

    override fun getViewBinding(): FragmentHistoryBinding =
        FragmentHistoryBinding.inflate(layoutInflater)

    private val resourcesProvider: DefaultCustomResourcesProvider by lazy {
        DefaultCustomResourcesProvider(requireContext())
    }

    private val adapter by lazy {
        ModelRecyclerAdapter<PointSavedHistory, HistoryViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            null
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPointSavedHistoryLiveData(8)

    }

    override fun initViews() {
        with(binding) {
            myPointRecyclerView.adapter = adapter
        }
    }

    override fun observeData() {
        with(binding) {
            viewModel.pointSavedHistoryLiveData.observe(this@HistoryFragment) { result ->
                result?.let {
                    Log.wtf("HistoryFragment", "size ${it.size}")
                    myPointRecyclerView.isVisible = it.count() > 0
                    adapter.submitList(it.toMutableList())

                } ?: kotlin.run {

                    myPointRecyclerView.isVisible = false

                }

                haveNoPointTxv.isVisible = !myPointRecyclerView.isVisible

            }
        }

    }

    fun TextView.setClick() {

    }
}