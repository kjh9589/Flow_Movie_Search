package com.kjs.domain.usecase.history

import com.kjs.domain.di.IoDispatcher
import com.kjs.domain.repository.history.HistorySearchKeywordRepository
import com.kjs.domain.usecase.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class InsertHistoryKeywordUseCase @Inject constructor(
    private val repository: HistorySearchKeywordRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): SuspendUseCase<InsertHistoryKeywordUseCase.Params, Unit>(dispatcher){
    override suspend fun execute(parameters: Params) {
        repository.insertKeyword(parameters)
    }

    data class Params(
        val keyword: String
    )
}