package com.bw.miaoheng20191231.presenter;

import com.bw.miaoheng20191231.base.mvp.Basepresenter;
import com.bw.miaoheng20191231.contract.IContract;
import com.bw.miaoheng20191231.emtity.InfoEntity;
import com.bw.miaoheng20191231.model.Model;

/**
 * 时间 :2019/12/31  9:00
 * 作者 :苗恒
 * 功能 :
 */
public class Presenter extends Basepresenter<Model, IContract.IView> implements IContract.IPresenter {
    @Override
    protected Model initModel() {
        return new Model();
    }
     //将m层获取到的数据传递给v层
    @Override
    public void getData(String url) {
          model.getData(url, new IContract.IModel.ModelCallBack() {
              @Override
              public void success(InfoEntity infoEntity) {
                  getView().success(infoEntity);
              }

              @Override
              public void failur(Throwable throwable) {
                 getView().failur(throwable);
              }
          });
    }
}
