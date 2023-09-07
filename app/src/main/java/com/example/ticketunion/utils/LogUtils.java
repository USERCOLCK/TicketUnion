package com.example.ticketunion.utils;


import android.util.Log;

public class LogUtils {
    //当前等级
    private static int currentLev = 4;
    //调试等级
    private static final int Debug_Lev = 4;
    //info等级
    private static final int Info_Lev = 3;
    //警告级别
    private static final int Warning_Lev = 2;
    //错误级别
    private static final int Error_Lev = 1;

    public static void d(Object object, String log) {
        if (currentLev >= Debug_Lev){
            Log.d(object.getClass().getSimpleName(),log);
        }
    }
    public static void i(Object object, String log) {
        if (currentLev >= Info_Lev){
            Log.i(object.getClass().getSimpleName(),log);
        }
    }
    public static void w(Object object, String log) {
        if (currentLev >= Warning_Lev){
            Log.w(object.getClass().getSimpleName(),log);
        }
    }
    public static void e(Object object, String log) {
        if (currentLev >= Error_Lev){
            Log.e(object.getClass().getSimpleName(),log);
        }
    }
}
