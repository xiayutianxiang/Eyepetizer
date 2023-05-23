package com.shanshan.eyepetizer.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoInfo(
    val videoId: Long, val playUrl: String, val title: String, val description: String, val category: String, val library: String,
    val consumption: Consumption, val cover: Cover2, val author: Author?, val webUrl: WebUrl
) : Parcelable
