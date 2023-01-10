package com.shanshan.eyepetizer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shanshan.eyepetizer.data.DiscoveryData
import com.shanshan.eyepetizer.data.repository.HomePageRepository
import com.shanshan.eyepetizer.utils.LogUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class DiscoveryViewModel : ViewModel() {

    private val discoveryRepo by lazy {
        HomePageRepository()
    }

    var discoveryData = MutableLiveData<DiscoveryData>()

    fun getDiscoveryData() {
        discoveryRepo.getHomeDiscoveryContent().enqueue(object : Callback<DiscoveryData> {
            override fun onResponse(
                call: Call<DiscoveryData>,
                response: Response<DiscoveryData>
            ) {
                val code = response.code()
                LogUtils.d(this.toString(), "response code -->${code}")
                if (code == HttpURLConnection.HTTP_OK) {
                    discoveryData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<DiscoveryData>, t: Throwable) {
                //TODO:处理加载失败情况
            }
        })
    }
}