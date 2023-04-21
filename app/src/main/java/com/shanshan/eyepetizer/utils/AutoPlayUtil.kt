package com.shanshan.eyepetizer.utils

import android.content.Context
import android.widget.ImageView
import com.shanshan.eyepetizer.ui.extension.load
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer

object AutoPlayUtil {

    fun autoPlay(
        player: GSYVideoPlayer,
        context: Context,
        playTag: String?,
        playUrl:String,
        position: Int,
        callback: GSYSampleCallBack?,
        imageUrl: String
    ) {
        player.run {
            setPlayTag(playTag)
            playPosition = position
            isReleaseWhenLossAudio = false

            val cover = ImageView(context)
            cover.load(imageUrl)
            thumbImageView = cover
            setUp(playUrl,false,null)
        }
    }
}