package com.shanshan.eyepetizer.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import com.gyf.immersionbar.ImmersionBar
import com.shanshan.eyepetizer.R
import com.shanshan.eyepetizer.contants.Constants
import com.shanshan.eyepetizer.data.VideoInfo
import com.shanshan.eyepetizer.databinding.ActivityDetailVideoBinding
import com.shanshan.eyepetizer.ui.extension.showToast
import com.shanshan.eyepetizer.utils.ResourceUtils
import com.shuyu.gsyvideoplayer.GSYVideoManager

class DetailVideoActivity : AppCompatActivity() {

    private var _binding: ActivityDetailVideoBinding? = null
    private val binding: ActivityDetailVideoBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setContentView(view: View?) {
        if (checkArguments()) {
            super.setContentView(view)
            setStatusBarBackground(R.color.black)
        }
    }

    //检查参数是否有效
    private fun checkArguments(): Boolean {
        return if (intent.getParcelableExtra<VideoInfo>(Constants.EXTRA_VIDEOINFO) == null && intent.getLongExtra(
                Constants.EXTRA_VIDEO_ID,
                0L
            ) == 0L
        ) {
            ResourceUtils.getString(R.string.jump_page_unknown_error).showToast()
            finish()
            false
        } else {
            true
        }
    }

    override fun onPause() {
        super.onPause()
        binding.videoPlayer.onVideoPause()
    }
                
    override fun onResume() {
        super.onResume()
        binding.videoPlayer.onVideoResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
        binding.videoPlayer.release()
        binding.videoPlayer.setVideoAllCallBack(null)
        _binding = null
    }

    private fun setStatusBarBackground(@ColorRes statusBarColor: Int) {
        ImmersionBar.with(this).autoStatusBarDarkModeEnable(true, 0.2f)
            .statusBarColor(statusBarColor).fitsSystemWindows(true).init()
    }


    companion object {
        const val TAG = "DetailVideoActivity"
    }
}