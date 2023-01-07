package com.kjs.domain.result

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow

enum class CommonResultState(val code: String) {
    INCORRECT_QUERY_REQUEST("SE01"),
    INVALID_DISPLAY_VALUE("SE02"),
    INVALID_START_VALUE("SE03"),
    INVALID_SORT_VALUE("SE04"),
    MALFORMED_ENCODING("SE06"),
    INVALID_SEARCH_API("SE05"),
    SYSTEM_ERROR("SE99"),
    FAILURE("FAILURE"),
    SUCCESS("SUCCESS"),
    LOADING("LOADING")
}

sealed class CommonResult<out T> {
    data class Success<out R>(val data: R): CommonResult<R>()
    data class Failure(val errorCode: CommonResultState = CommonResultState.FAILURE, val exception: Exception? = null): CommonResult<Nothing>()
    object Loading: CommonResult<Nothing>()
    object None: CommonResult<Nothing>()

    override fun toString(): String {
        return when(this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Failure[errorCode=$errorCode, exception=$exception"
            is Loading -> "Loading"
            is None -> "None"
        }
    }
}

val CommonResult<*>.succeeded
    get() = this is CommonResult.Success && data != null

fun <R> CommonResult<R>.successOr(fallback: R): R {
    return (this as? CommonResult.Success<R>)?.data ?: fallback
}

val <R> CommonResult<R>.data: R?
    get() = (this as? CommonResult.Success)?.data

@JvmName("updateOnSuccessT")
inline fun <reified T> CommonResult<T>.updateOnSuccess(liveData: MutableLiveData<T>) {
    if (this is CommonResult.Success) {
        liveData.value = data
    }
}

inline fun <reified T> CommonResult<T>.updateOnSuccess(stateFlow: MutableStateFlow<T>) {
    if (this is CommonResult.Success) {
        stateFlow.value = data
    }
}