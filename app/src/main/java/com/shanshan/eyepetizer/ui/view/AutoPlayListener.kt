package com.shanshan.eyepetizer.ui.view

import android.annotation.SuppressLint
import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shanshan.eyepetizer.adapter.home.community.FollowAdapter
import com.shuyu.gsyvideoplayer.GSYVideoManager

/**
 * 自动播放的监听类
 */
class AutoPlayListener(val activity: Activity?,val adapter: FollowAdapter?) : RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            playVideo(recyclerView)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        //可见区域的第一个item
        val firstPosition = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        //可见区域的最后一个item
        val lastPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

        //播放的位置
        val playPosition = GSYVideoManager.instance().playPosition

        if (playPosition >=0 ){ //说明有播放
            if (playPosition<firstPosition || playPosition > lastPosition) {
                if (GSYVideoManager.isFullState(activity)) {
                    return
                }
                GSYVideoManager.releaseAllVideos()
                adapter?.notifyDataSetChanged()
            }
        }
    }

    private fun playVideo(recyclerView: RecyclerView) {

    }
}