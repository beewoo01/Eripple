package com.codebros.eripple.screen.main.setting.faq

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.model.CellType
import com.codebros.eripple.model.question.Question
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class FAQViewModel : BaseViewModel(){

    private val _faqListLiveData = MutableLiveData<List<Question>?>()
    val faqListLiveData : LiveData<List<Question>?> = _faqListLiveData

    fun getFAQ() = viewModelScope.launch(exceptionhandler) {
        val response = repository.getFAQ()

        if (response.isSuccessful) {

            val result = response.body()
            _faqListLiveData.value = result?.map { questionEntity ->
                Question(
                    uid = questionEntity.hashCode().toLong(),
                    type = CellType.FAQ_CELL,
                    question_idx = questionEntity.question_idx,
                    question_title = questionEntity.question_title,
                    question_contents = questionEntity.question_contents,
                    question_createtime = questionEntity.question_createtime,
                    question_updatetime = questionEntity.question_updatetime
                )
            }
        }
    }
}