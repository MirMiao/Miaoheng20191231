package com.bw.miaoheng20191231.contract;

import com.bw.miaoheng20191231.base.mvp.IBaseModel;
import com.bw.miaoheng20191231.base.mvp.IBaseView;
import com.bw.miaoheng20191231.emtity.InfoEntity;

/**
 * 时间 :2019/12/31  8:57
 * 作者 :苗恒
 * 功能 :  同一契约管理接口类
 */
public interface IContract {

    interface IModel extends IBaseModel {
        void getData(String url,ModelCallBack modelCallBack);
        interface ModelCallBack{
            void success(InfoEntity infoEntity);
            void failur(Throwable throwable);
        }
    }
    interface IView extends IBaseView{
        void success(InfoEntity infoEntity);
        void failur(Throwable throwable);
    }
    interface IPresenter{
        void getData(String url);
    }
}
