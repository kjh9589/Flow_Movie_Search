package com.kjs.domain.usecase.history

import com.kjs.domain.di.IoDispatcher
import com.kjs.domain.repository.history.HistorySearchKeywordRepository
import com.kjs.domain.usecase.SuspendUseCase
import com.kjs.model.history.HistorySearchKeywordModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetHistoryKeywordListUseCase @Inject constructor(
    private val repository: HistorySearchKeywordRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): SuspendUseCase<Unit, List<HistorySearchKeywordModel>>(dispatcher) {
    override suspend fun execute(parameters: Unit): List<HistorySearchKeywordModel> {
        return repository.getHistoryKeywordList()
    }
}