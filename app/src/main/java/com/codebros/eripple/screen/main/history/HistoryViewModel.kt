package com.codebros.eripple.screen.main.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.data.entity.PointSavedEntity
import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.pointsavedhistory.PointSavedHistory
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class HistoryViewModel : BaseViewModel() {

    private val _pointSavedHistoryLiveData = MutableLiveData<List<PointSavedHistory>?>()
    val pointSavedHistoryLiveData: LiveData<List<PointSavedHistory>?> = _pointSavedHistoryLiveData

    fun getPointSavedHistoryLiveData(account_idx: Int) = viewModelScope.launch {
        val response = repository.getPointHistory(account_idx)

        if (response.isSuccessful) {

            val result = response.body()
            _pointSavedHistoryLiveData.value = result?.map { model ->
                PointSavedHistory(
                    uid = model.hashCode().toLong(),
                    type = CellType.POINT_SAVED,
                    waste_discharge_record_idx = model.waste_discharge_record_idx,
                    eripple_idx = model.eripple_idx,
                    waste_discharge_record_earned_points = model.waste_discharge_record_earned_points,
                    waste_discharge_record_gram = model.waste_discharge_record_gram,
                    eripple_name = model.eripple_name,
                    eripple_address = model.eripple_address,
                    eripple_address_detail = model.eripple_address_detail,
                    waste_discharge_record_updatetime = model.waste_discharge_record_updatetime,
                    viewStatus = false
                )
            }

        } else {
            _pointSavedHistoryLiveData.value = null
        }

    }

}