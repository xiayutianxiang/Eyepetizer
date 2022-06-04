package com.shanshan.eyepetizer.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class BaseApplication : Application() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        private lateinit var app:Context

        fun getContext():Context{
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = applicationContext
    }
}