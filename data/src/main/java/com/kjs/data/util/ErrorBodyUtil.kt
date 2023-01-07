package com.kjs.data.util

import com.google.gson.Gson
import com.kjs.data.response.ErrorResponse

fun String.parseErrorBody(): ErrorResponse {
    val gson = Gson()
    return gson.fromJson(this, ErrorResponse::class.java)
}