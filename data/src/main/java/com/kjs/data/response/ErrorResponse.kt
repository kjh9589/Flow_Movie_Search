package com.kjs.data.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("errorMessage")
    val errorMessage: String = "",
    @SerializedName("errorCode")
    val errorCode: String = "",
)