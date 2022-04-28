package com.codebros.eripple.screen.main.eripple_info

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.codebros.eripple.databinding.FragmentErippleInfoBinding
import com.codebros.eripple.model.Model
import com.codebros.eripple.model.eripple.Eripple
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.util.observeOnce
import com.codebros.eripple.util.provider.DefaultCustomResourcesProvider
import com.codebros.eripple.widget.adapter.ModelRecyclerAdapter
import com.codebros.eripple.widget.adapter.listener.eripple.ErippleAdapterListener
import java.util.*

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
                override fun onItemClick(model: Eripple) {
                    Log.wtf(
                        "ErippleAdapterListener",
                        "onItemClick bookmark IDx ${model.bookmark_idx}"
                    )
                }

                override fun onHeartClick(model: Eripple) {

                    if (model.bookmark_idx > 0) {
                        viewModel.removeBookMark(model)
                    } else {
                        viewModel.addBookMark(model, 1)
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
            erippleContainer.searchEdt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

                override fun afterTextChanged(p0: Editable?) {

                    viewModel.erippleList.value?.let {

                        if (it.count() > 0) {

                            search(erippleContainer.searchEdt.text.toString())
                        }

                    } ?: kotlin.run {

                    }

                }

            })
        }
    }

    private fun search(text: String) {
        Log.wtf("ErippleInfoFragment", "search ")
        Log.wtf("ErippleInfoFragment", "search $text")

        val list = mutableListOf<Model>()

        viewModel.erippleList.value?.let {
            if (text.isEmpty()) {

                list.addAll(it)

            } else {

                val currentList = mutableListOf<Model>()
                currentList.addAll(it)
                Log.wtf("ErippleInfoFragment", "list size ${currentList.size}")

                currentList.forEach { model ->
                    if (model is Eripple) {

                        val name =
                            model.eripple_name.replace(" ", "").lowercase(Locale.getDefault())
                                .contains(text.replace(" ", ""))

                        val address =
                            model.eripple_address.replace(" ", "").lowercase(Locale.getDefault())
                                .contains(text.replace(" ", ""))

                        if (name) {
                            list.add(model)
                        } else if (address) {
                            list.add(model)
                        }

                    }

                }
            }
        }

        adapter.submitList(list)

    }

    override fun observeData() {
        viewModel.erippleList.observe(this@ErippleInfoFragment) {
            adapter.currentList.toMutableList().clear()
            adapter.submitList(it?.toMutableList())
        }

        viewModel.erippleAddedBookmarkState.observe(this@ErippleInfoFragment) {

            it.second?.let { changeModel ->

                val list: MutableList<Model> = adapter.currentList
                val position = list.indexOf(it.first)

                if (position > -1) {
                    if (list[position] is Eripple) {
                        (list[position] as Eripple).bookmark_idx = changeModel.bookmark_idx
                    }
                    adapter.submitList(list)
                    adapter.notifyItemChanged(position)
                }


                Toast.makeText(
                    requireContext(),
                    "${changeModel.eripple_name}를(을) 즐겨찾기에 등록하였습니다.",
                    Toast.LENGTH_SHORT
                ).show()

            } ?: kotlin.run {

                Toast.makeText(requireContext(), "즐겨찾기 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show()

            }
        }

        viewModel.erippleRemovedState.observe(this@ErippleInfoFragment) {

            it?.let { result ->

                val list: MutableList<Model> = mutableListOf()
                list.addAll(adapter.currentList)
                val position = list.indexOf(result)

                if (position > -1) {
                    result.bookmark_idx = 0
                    list[position] = result
                    adapter.submitList(list)
                    adapter.notifyItemChanged(position)
                    Toast.makeText(
                        requireContext(),
                        "${result.eripple_name}를(을) 즐겨찾기에서 삭제하였습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }


            } ?: kotlin.run {
                Toast.makeText(requireContext(), "즐겨찾기 삭제에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getErippleList(1)
    }

}
