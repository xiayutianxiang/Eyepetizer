package com.shanshan.eyepetizer.utils;

import android.util.Log;

public class LogUtils {

    private final String TAG = "com.shanshan.eyepetizer";

    private static final boolean DEBUG = true;

    public static void d(String tag,String msg){
        if(DEBUG){
            Log.d(tag,msg);
        }
    }

    public static void e(String tag,String msg){
        if(DEBUG){
            Log.d(tag,msg);
        }
    }

    public static void i(String tag,String msg){
        if(DEBUG){
            Log.d(tag,msg);
        }
    }

    public static void v(String tag,String msg){
        if(DEBUG){
            Log.d(tag,msg);
        }
    }

    public static void w(String tag,String msg){
        if(DEBUG){
            Log.d(tag,msg);
        }
    }
}
