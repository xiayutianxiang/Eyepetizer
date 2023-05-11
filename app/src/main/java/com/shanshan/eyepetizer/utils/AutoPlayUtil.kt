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
        imageUrl: String,
        callback: GSYSampleCallBack?
    ) {
        player.run {
            setPlayTag(playTag)     //防止错位
            playPosition = position     //设置播放位置防止错位
            isReleaseWhenLossAudio = false  //音频焦点冲突时是否释放
            isLooping = false       //循环播放
            val cover = ImageView(context)
            cover.load(imageUrl)
            thumbImageView = cover          //封面
            setVideoAllCallBack(callback)   //播放过程中的回调
            setUp(playUrl,false,null)   //播放url
        }
    }
}