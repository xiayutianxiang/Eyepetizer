package com.shanshan.eyepetizer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shanshan.eyepetizer.data.VideoInfo
import com.shanshan.eyepetizer.network.api.VideoService

class DetailViewModel : ViewModel() {

    var videoInfoData: VideoInfo? = null
    private var videoDetailLiveData_ = MutableLiveData<RequestParam>()
    private var repliesAndRepliesLiveData_ = MutableLiveData<RequestParam>()
    var videoId: Long = 0L
    var nextPageUrl: String? = null

    fun onRefresh() {
        if (videoInfoData == null) {
            videoDetailLiveData_.value =
                RequestParam(videoId, "${VideoService.VIDEO_REPLIES_URL}$videoId")
        } else {
            repliesAndRepliesLiveData_.value = RequestParam(videoInfoData?.videoId ?: 0L, "${VideoService.VIDEO_REPLIES_URL}${videoInfoData?.videoId ?: 0L}")

        }
    }


    inner class RequestParam(val videoId: Long, val repliesUrl: String)
}