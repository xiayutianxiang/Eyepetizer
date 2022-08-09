package com.shanshan.eyepetizer.utils;

import android.content.res.Resources;
import android.graphics.Color;

import com.shanshan.eyepetizer.base.BaseApplication;


public class ResourceUtils {

    public static int getColor(int resId) throws Resources.NotFoundException {
        try {
            return BaseApplication.Companion.getContext().getResources().getColor(resId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return getColor("000000");
    }

    public static int getColor(String color) throws Resources.NotFoundException {
        return Color.parseColor(color);
    }

    public static String getString(int resId) throws Resources.NotFoundException {
        try {
            return BaseApplication.Companion.getContext().getResources().getString(resId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getString(int resId, Object... formatArgs) {
        try {
            return BaseApplication.Companion.getContext().getResources().getString(resId, formatArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getDimen(int resId){
        return BaseApplication.Companion.getContext().getResources().getDimensionPixelOffset(resId);
    }
}
