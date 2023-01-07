package com.kjs.flow.presentation.common

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class CommonActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("Flow", "----------CREATE ACTIVITY : \" + ${this::class.simpleName} + \"----------")
    }

    open fun initViews() {}
    open fun bindViews() {}
}