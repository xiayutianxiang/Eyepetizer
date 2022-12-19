package com.shanshan.eyepetizer.model.repository

import com.shanshan.eyepetizer.contants.Constants
import com.shanshan.eyepetizer.network.RetrofitManager
import retrofit2.await

class DiscoveryRepository {

   //获取发现页
   fun getDiscoveryContent() = RetrofitManager.apiService.getDiscovery(Constants.WebUrl.DISCOVERY_URL)
}