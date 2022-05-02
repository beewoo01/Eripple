package com.codebros.eripple.screen.main.my_point.exchange

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.codebros.eripple.databinding.FragmentExchangeHistoryBinding
import com.codebros.eripple.model.exchange.AccountExchangeHistory
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.util.provider.DefaultCustomResourcesProvider
import com.codebros.eripple.widget.adapter.ModelRecyclerAdapter

class ExchangeHistoryFragment :
    BaseFragment<ExchangeHistoryViewModel, FragmentExchangeHistoryBinding>() {

    override val viewModel: ExchangeHistoryViewModel by viewModels()

    override fun getViewBinding(): FragmentExchangeHistoryBinding =
        FragmentExchangeHistoryBinding.inflate(layoutInflater)


    private val resourcesProvider: DefaultCustomResourcesProvider by lazy {
        DefaultCustomResourcesProvider(requireContext())
    }

    private val adapter by lazy {
        ModelRecyclerAdapter<AccountExchangeHistory, ExchangeHistoryViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            adapterListener = null
        )
    }

    override fun observeData() {
        viewModel.exChangeHistoryLiveData.observe(this@ExchangeHistoryFragment) {

            it?.let { result ->

                adapter.submitList(result.toMutableList())


            } ?: kotlin.run {


            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getExchangeHistory(1)
        binding.exchangeHistoryRecyclerView.adapter = adapter
    }


    companion object {

        const val TAG = "ExchangeHistoryFragment"

        fun newInstance(): Fragment {
            return ExchangeHistoryFragment()
        }
    }
}