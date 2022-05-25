package com.codebros.eripple.screen.main.home.alarm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.alarm.AlarmModel
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AlarmViewModel : BaseViewModel() {

    private val _alarmLivaData = MutableLiveData<List<AlarmModel>?>()
    val alarmList : LiveData<List<AlarmModel>?> = _alarmLivaData

    private val _alarmStateLiveData = MutableLiveData<Int?>()
    val alarmState : LiveData<Int?> = _alarmStateLiveData

    fun getAlarmList(account_idx : Int) : Job = viewModelScope.launch {
        val response = repository.getAlarm(account_idx)

        if (response.isSuccessful) {

            val result = response.body()
            _alarmLivaData.value = result?.map {
                AlarmModel(
                    uid = it.hashCode().toLong(),
                    type = CellType.ALARM_CELL,
                    alarm_idx = it.alarm_idx,
                    account_account_idx = account_idx,
                    eripple_eripple_idx = it.eripple_eripple_idx,
                    alarm_content = it.alarm_content,
                    alarm_type = it.alarm_type,
                    alarm_createtime = it.alarm_createtime,
                    alarm_updatetime = it.alarm_updatetime,
                    eripple_name = it.eripple_name
                )
            }
        } else {
            _alarmLivaData.value = null
        }
    }

    fun alarmStateUpdate(alarmListStr : String) : Job = viewModelScope.launch{

        val response = repository.updateAlarmState(alarmListStr)
        if (response.isSuccessful) {
            _alarmStateLiveData.value = response.body()
        }


    }

}