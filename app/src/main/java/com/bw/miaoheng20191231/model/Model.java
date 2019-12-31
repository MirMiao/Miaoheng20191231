package com.bw.miaoheng20191231.model;

import com.bw.miaoheng20191231.contract.IContract;
import com.bw.miaoheng20191231.emtity.InfoEntity;
import com.bw.miaoheng20191231.util.OkHttpUtil;
import com.google.gson.Gson;

/**
 * 时间 :2019/12/31  8:57
 * 作者 :苗恒
 * 功能 :  数据模型层
 */
public class Model implements IContract.IModel {

    @Override
    public void getData(String url, ModelCallBack modelCallBack) {
        //使用okhttp工具类获取数据
        OkHttpUtil.getInstance().doGet(url, new OkHttpUtil.OkCallBack() {
            @Override
            public void success(String response) {
                InfoEntity infoEntity = new Gson().fromJson(response, InfoEntity.class);
                modelCallBack.success(infoEntity);
            }

            @Override
            public void failur(Throwable throwable) {
                 modelCallBack.failur(throwable);
            }
        });
    }
}
