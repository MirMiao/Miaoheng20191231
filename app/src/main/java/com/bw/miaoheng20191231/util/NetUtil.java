package com.bw.miaoheng20191231.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 时间 :2019/12/31  9:42
 * 作者 :苗恒
 * 功能 : 判断当前有没有网络
 */
public class NetUtil {
    private static NetUtil netUtil=new NetUtil();

    private NetUtil() {
    }
    //单例模式
    public static NetUtil getInstance() {
        return netUtil;
    }
    public boolean hasNet(Context context){
        //得到网络管理类
     ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        //当前当前网络是否可用
        if (activeNetworkInfo != null&&activeNetworkInfo.isAvailable()) {
            return true;
        }else{
            return false;
        }
    }
}
