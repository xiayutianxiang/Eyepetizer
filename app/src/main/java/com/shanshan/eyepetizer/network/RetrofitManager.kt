package com.shanshan.eyepetizer.network

import com.shanshan.eyepetizer.contants.Constants
import com.shanshan.eyepetizer.network.api.ApiService
import com.shanshan.eyepetizer.utils.LogUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object RetrofitManager {

    private val okhttpClient = OkHttpClient.Builder()
        .callTimeout(300,TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    private val mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = mRetrofit.create(ApiService::class.java)

    class LoggingInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val t1 = System.nanoTime()
            LogUtils.d(TAG, "Sending request: ${request.url()} \n ${request.headers()}")

            val response = chain.proceed(request)

            val t2 = System.nanoTime()
            LogUtils.d(
                TAG,
                "Received response for  ${
                    response.request().url()
                } in ${(t2 - t1) / 1e6} ms\n${response.headers()}"
            )
            return response
        }

        companion object {
            const val TAG = "LoggingInterceptor"
        }
    }
}