package com.kjs.flow.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kjs.domain.result.data
import com.kjs.domain.usecase.history.GetHistoryKeywordListUseCase
import com.kjs.domain.usecase.history.InsertHistoryKeywordUseCase
import com.kjs.domain.usecase.movie.SearchNaverMovieUseCase
import com.kjs.flow.R
import com.kjs.model.movie.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchNaverMovieUseCase: SearchNaverMovieUseCase,
    private val insertHistoryKeywordUseCase: InsertHistoryKeywordUseCase
) : ViewModel() {

    private val _movieList = MutableStateFlow<PagingData<MovieModel>>(PagingData.empty())
    val movieList: StateFlow<PagingData<MovieModel>>
        get() = _movieList

    private val _error = MutableStateFlow("")
    val error: StateFlow<String>
        get() = _error

    fun searchMovie(keyword: String, errorMsg: String) {
        viewModelScope.launch {
            if (keyword.trim().isEmpty()) {
                _error.value = errorMsg
                return@launch
            }

            _movieList.value = PagingData.empty()

            searchNaverMovieUseCase.invoke(keyword)
                .onSuccess {
                    insertHistoryKeywordUseCase.invoke(
                        InsertHistoryKeywordUseCase.Params(keyword)
                    ).onSuccess {

                    }.onFailure {

                    }

                    it.cachedIn(viewModelScope).collectLatest { res ->
                        _movieList.value = res
                    }
                }
        }
    }

    fun clearHolder() {
        _error.value = ""
    }
}