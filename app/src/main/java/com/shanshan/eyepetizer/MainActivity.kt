package com.shanshan.eyepetizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.pixplicity.sharp.Sharp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val img = findViewById<ImageView>(R.id.loadsvg)
        Sharp.loadPath("http://img.svgfont.com/2021/11/13/svgfont_83de5fdb2720211113104828416.png?x-oss-process=style/large")

        /*Glide.with(this).load("http://img.svgfont.com/2021/11/13/svgfont_83de5fdb2720211113104828416.png?x-oss-process=style/large")
            .into(img)*/
    }
}