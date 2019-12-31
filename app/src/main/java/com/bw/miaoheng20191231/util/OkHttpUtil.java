package com.bw.miaoheng20191231.util;


import android.os.Handler;

import com.bw.miaoheng20191231.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 时间 :2019/12/31  8:53
 * 作者 :苗恒
 * 功能 :okttp的网络工具类
 */
public class OkHttpUtil {
    Handler handler=new Handler();
    private static OkHttpUtil okHttpUtil;

    private OkHttpUtil() {
    }
 //使用二层校验锁
    public static OkHttpUtil getInstance() {
        if(okHttpUtil==null){
            synchronized (OkHttpUtil.class){
                if(okHttpUtil==null){
                    okHttpUtil=new OkHttpUtil();
                }
            }
        }
        return okHttpUtil;
    }
    //get请求
    public void doGet(String url,OkCallBack okCallBack){
        //使用构建者模式
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))//添加日志拦截器
                .build();
        Request request=new Request.Builder()
                .url(url)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                //线程切换
                 handler.post(new Runnable() {
                     @Override
                     public void run() {
                        okCallBack.failur(e);
                     }
                 });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                   handler.post(new Runnable() {
                       @Override
                       public void run() {
                           try {
                               okCallBack.success(response.body().string());
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                       }
                   });
            }
        });
    }
    //自定义接口回调
    public interface OkCallBack{
        void success(String response);
        void failur(Throwable throwable);
    }
}
