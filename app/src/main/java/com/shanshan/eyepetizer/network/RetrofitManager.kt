package com.shanshan.eyepetizer.network

import android.os.Build
import com.shanshan.eyepetizer.contants.Constants
import com.shanshan.eyepetizer.network.api.ApiService
import com.shanshan.eyepetizer.utils.GlobalUtil
import com.shanshan.eyepetizer.utils.LogUtils
import com.shanshan.eyepetizer.utils.screenPixel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

object RetrofitManager {

    private val okhttpClient = OkHttpClient.Builder()
        .callTimeout(300,TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor(LoggingInterceptor())
        .addInterceptor(HeaderInterceptor())
        .addInterceptor(BasicParamsInterceptor())
        .build()

    private val mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.WebUrl.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttpClient)
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

    class BasicParamsInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url()
            val url = originalHttpUrl.newBuilder().apply {
               // addQueryParameter("udid", GlobalUtil.getDeviceSerial())
                //针对开眼官方【首页推荐 】api 变动， 需要单独做处理。原因：附加 vc、vn 这两个字段后，请求接口无响应。
                if (!originalHttpUrl.toString().contains(Constants.WebUrl.RECOMMEND_URL)) {
                    addQueryParameter("vc", GlobalUtil.eyepetizerVersionCode.toString())
                    addQueryParameter("vn", GlobalUtil.eyepetizerVersionName)
                }
                addQueryParameter("size", screenPixel())
                addQueryParameter("deviceModel", GlobalUtil.deviceModel)
                addQueryParameter("first_channel", GlobalUtil.deviceBrand)
                addQueryParameter("last_channel", GlobalUtil.deviceBrand)
                addQueryParameter("system_version_code", "${Build.VERSION.SDK_INT}")
            }.build()
            val request = originalRequest.newBuilder().url(url).build()
            return chain.proceed(request)
        }
    }

    class HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val request = originalRequest.newBuilder().apply {
                header("model", "Android")
                header("If-Modified-Since", "${Date()}")
                header("User-Agent", System.getProperty("http.agent") ?: "unknown")
            }.build()
            return chain.proceed(request)
        }
    }
}