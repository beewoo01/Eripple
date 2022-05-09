package com.codebros.eripple.screen.main.setting.faq

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.codebros.eripple.databinding.FragmentFAQBinding
import com.codebros.eripple.model.question.Question
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.util.provider.DefaultCustomResourcesProvider
import com.codebros.eripple.widget.adapter.ModelRecyclerAdapter


class FAQFragment : BaseFragment<FAQViewModel, FragmentFAQBinding>() {


    override val viewModel: FAQViewModel by viewModels()

    override fun getViewBinding(): FragmentFAQBinding = FragmentFAQBinding.inflate(layoutInflater)

    private val resourcesProvider: DefaultCustomResourcesProvider by lazy {
        DefaultCustomResourcesProvider(requireContext())
    }

    private val adapter by lazy {
        ModelRecyclerAdapter<Question, FAQViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            null
        )
    }

    override fun observeData() {

        viewModel.faqListLiveData.observe(this@FAQFragment) { result ->
            Log.wtf("FAQFragment", "result? ${result.toString()}")
            adapter.submitList(result?.toMutableList())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFAQ()
    }

    override fun initViews() = with(binding) {
        super.initViews()

        recyclerviewFaq.adapter = adapter

        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

    }


}