package com.codebros.eripple.screen.main.eripple_info

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.codebros.eripple.databinding.FragmentErippleInfoBinding
import com.codebros.eripple.model.eripple.Eripple
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.util.observeOnce
import com.codebros.eripple.util.provider.DefaultCustomResourcesProvider
import com.codebros.eripple.widget.adapter.ModelRecyclerAdapter
import com.codebros.eripple.widget.adapter.listener.eripple.ErippleAdapterListener

class ErippleInfoFragment : BaseFragment<ErippleInfoViewModel, FragmentErippleInfoBinding>() {

    override val viewModel: ErippleInfoViewModel by viewModels()

    override fun getViewBinding(): FragmentErippleInfoBinding =
        FragmentErippleInfoBinding.inflate(layoutInflater)

    private val resourcesProvider: DefaultCustomResourcesProvider by lazy {
        DefaultCustomResourcesProvider(requireContext())
    }

    private val adapter by lazy {
        ModelRecyclerAdapter<Eripple, ErippleInfoViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,

            object : ErippleAdapterListener {
                override fun onHeartClick(model: Eripple) {

                    if (model.bookmark_idx > 0) {
                        viewModel.addBookMark(1, model.eripple_idx)

                        viewModel.erippleAddedBookmarkState.observe(this@ErippleInfoFragment) {

                        }

                    } else {
                        viewModel.removeBookMark(model.bookmark_idx)
                    }

                }

                override fun onShearClick(model: Eripple) {
                    // TODO: 공유하기

                }
            }
        )
    }

    override fun initViews() {
        with(binding) {
            erippleContainer.erippleRecyclerView.adapter = adapter
        }
    }

    override fun observeData() {
        viewModel.erippleList.observe(this@ErippleInfoFragment) {
            adapter.submitList(it?.toMutableList())
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getErippleList(1)
    }

}
