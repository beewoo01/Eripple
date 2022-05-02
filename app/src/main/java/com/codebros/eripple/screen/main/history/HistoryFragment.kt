package com.codebros.eripple.screen.main.history

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentHistoryBinding
import com.codebros.eripple.model.Model
import com.codebros.eripple.model.pointsavedhistory.PointSavedHistory
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.util.DateType
import com.codebros.eripple.util.provider.DefaultCustomResourcesProvider
import com.codebros.eripple.widget.adapter.ModelRecyclerAdapter
import jp.wasabeef.glide.transformations.internal.Utils
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

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

    private val chipTextViewArray = arrayOfNulls<TextView>(5)
    private var checkedId = R.id.chipToday


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPointSavedHistoryLiveData(1)
    }

    @SuppressLint("SimpleDateFormat")
    private fun chipClick(id: Int) {
        Log.wtf("chipClick", "chipClick $id")
        checkedId = id
        for ((position, textView) in chipTextViewArray.withIndex()) {
            if (id == textView?.id) {

                chipTextViewArray[position]?.run {
                    background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_circle)
                    setTextColor(resourcesProvider.getColor(R.color.white))
                }


            } else {

                chipTextViewArray[position]?.run {
                    background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.bg_circle_unselected)
                    setTextColor(resourcesProvider.getColor(R.color.defaultEditHintColor))
                }

            }
        }

        val list = mutableListOf<PointSavedHistory>()
        val changeList = mutableListOf<PointSavedHistory>()

        viewModel.pointSavedHistoryLiveData.value?.let {
            list.addAll(it)
        }

        Log.wtf("chipClick", "list size  ${list.size}")

        if (id == R.id.chipTotal) {

            changeList.addAll(list)

        } else {
            Log.wtf("chipClick", "list else")
            for (model in list) {
                val currentDate = Calendar.getInstance()
                val modelData = Calendar.getInstance()
                val sdf = SimpleDateFormat("yyyy.MM.dd")
                val date = sdf.parse(model.waste_discharge_record_updatetime)

                if (date != null) {
                    modelData.time = date
                }

                val diffSec: Long = (currentDate.timeInMillis - modelData.timeInMillis) / 1000
                val diffDays: Int = (diffSec / (24 * 60 * 60)).toInt() //일자수 차이

                Log.wtf("chipClick", "diffDays $diffDays")


                when (id) {

                    R.id.chipToday -> {
                        if (diffDays == 0) {
                            changeList.add(model)
                        }

                    }

                    R.id.chipYesterday -> {
                        if (diffDays == 1) {
                            changeList.add(model)
                        }

                    }

                    R.id.chipWeek -> {
                        if (diffDays in 0..7) {
                            changeList.add(model)
                        }

                    }

                    R.id.chipMonth -> {
                        if (diffDays in 0..30) {
                            changeList.add(model)
                        }

                    }

                }


            }
        }

        adapter.submitList(changeList.toMutableList())


    }


    override fun initViews() {
        with(binding) {

            myPointRecyclerView.adapter = adapter
            chipTextViewArray[0] = binding.chipToday
            chipTextViewArray[1] = binding.chipYesterday
            chipTextViewArray[2] = binding.chipWeek
            chipTextViewArray[3] = binding.chipMonth
            chipTextViewArray[4] = binding.chipTotal

            chipToday.setOnClickListener {
                chipClick(it.id)
            }

            chipYesterday.setOnClickListener {
                chipClick(it.id)
            }

            chipWeek.setOnClickListener {
                chipClick(it.id)
            }

            chipMonth.setOnClickListener {
                chipClick(it.id)
            }

            chipTotal.setOnClickListener {
                chipClick(it.id)
            }

        }
    }

    override fun observeData() {

        with(binding) {

            viewModel.pointSavedHistoryLiveData.observe(this@HistoryFragment) { result ->
                result?.let {

                    myPointRecyclerView.isVisible = it.count() > 0

                    //adapter.submitList(it.toMutableList())
                    chipClick(checkedId)

                } ?: kotlin.run {

                    myPointRecyclerView.isVisible = false

                }

                haveNoPointTxv.isVisible = !myPointRecyclerView.isVisible

            }
        }

    }
}