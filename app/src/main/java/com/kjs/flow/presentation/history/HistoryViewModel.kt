package com.kjs.flow.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kjs.domain.usecase.history.GetHistoryKeywordListUseCase
import com.kjs.model.history.HistorySearchKeywordModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getHistoryKeywordListUseCase: GetHistoryKeywordListUseCase
): ViewModel() {
    private val _historyList = MutableLiveData<List<HistorySearchKeywordModel>>()
    val historyList: LiveData<List<HistorySearchKeywordModel>>
        get() = _historyList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun getHistoryKeyword() {
        viewModelScope.launch {
            getHistoryKeywordListUseCase.invoke(Unit)
                .onSuccess {
                    _historyList.value = it
                }
                .onFailure {
                    _error.value = it.message
                }
        }
    }

    fun clearHolder() {
        _error.value = ""
    }
}