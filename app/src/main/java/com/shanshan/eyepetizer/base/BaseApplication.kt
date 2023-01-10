package com.shanshan.eyepetizer.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.shanshan.eyepetizer.R

class BaseApplication : Application() {

    init {
        SmartRefreshLayout.setDefaultRefreshInitializer { context, layout ->
            layout.setEnableLoadMore(true)
            layout.setEnableLoadMoreWhenContentNotFull(true)
        }

        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setEnableHeaderTranslationContent(true)
            MaterialHeader(context).setColorSchemeResources(R.color.blue, R.color.blue, R.color.blue)
        }
    }

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