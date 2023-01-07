package com.kjs.data.util

import com.kjs.data.common.CommonApiResult
import com.kjs.domain.result.CommonResultState
import retrofit2.Response

fun <T> Response<T>.convertCommonApiResult(): CommonApiResult<T> {
    return if (this.isSuccessful) {
        CommonApiResult.Success(this.body())
    } else {
        val errorResponse = this.errorBody()?.string()?.parseErrorBody()!!
        this.errorBody()?.close()

        val status = when(errorResponse.errorCode) {
            CommonResultState.INCORRECT_QUERY_REQUEST.code -> {
                CommonResultState.INCORRECT_QUERY_REQUEST
            }
            CommonResultState.INVALID_DISPLAY_VALUE.code -> {
                CommonResultState.INVALID_DISPLAY_VALUE
            }
            CommonResultState.INVALID_START_VALUE.code -> {
                CommonResultState.INVALID_START_VALUE
            }
            CommonResultState.INVALID_SORT_VALUE.code -> {
                CommonResultState.INVALID_SORT_VALUE
            }
            CommonResultState.MALFORMED_ENCODING.code -> {
                CommonResultState.MALFORMED_ENCODING
            }
            CommonResultState.INVALID_SEARCH_API.code -> {
                CommonResultState.INVALID_SEARCH_API
            }
            CommonResultState.SYSTEM_ERROR.code -> {
                CommonResultState.SYSTEM_ERROR
            }
            else -> {
                CommonResultState.FAILURE
            }
        }

        CommonApiResult.Failure(status, errorResponse.errorMessage)
    }
}