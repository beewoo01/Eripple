package com.codebros.eripple.screen.main.home.alarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.codebros.eripple.R
import com.codebros.eripple.databinding.FragmentAlarmBinding
import com.codebros.eripple.model.alarm.AlarmModel
import com.codebros.eripple.screen.base.BaseFragment
import com.codebros.eripple.util.AccountInfoSingleton
import com.codebros.eripple.util.provider.DefaultCustomResourcesProvider
import com.codebros.eripple.widget.adapter.ModelRecyclerAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class AlarmFragment : BaseFragment<AlarmViewModel, FragmentAlarmBinding>() {

    override val viewModel: AlarmViewModel by viewModels()

    override fun getViewBinding(): FragmentAlarmBinding =
        FragmentAlarmBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.alarmList.observe(this@AlarmFragment) {
            it?.let { adapterList ->
                alarmAdapter.submitList(adapterList.toMutableList())
                if (adapterList.count() > 0) {
                    val sendList = adapterList.map { model->
                        model.toDto()
                    }
                    val gson : Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                    val alarmListToGson = gson.toJson(sendList)
                    viewModel.alarmStateUpdate(alarmListToGson)

                }
            }

        }

        viewModel.alarmState.observe(this@AlarmFragment) {

        }
    }

    private val resourcesProvider: DefaultCustomResourcesProvider by lazy {
        DefaultCustomResourcesProvider(requireContext())
    }

    private val alarmAdapter by lazy {
        ModelRecyclerAdapter<AlarmModel, AlarmViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            adapterListener = null
        )
    }

    override fun initViews() = with(binding) {
        super.initViews()
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        recyclerviewAlarm.adapter = alarmAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AccountInfoSingleton.account_idx?.let {
            viewModel.getAlarmList(it)
        }


    }


}