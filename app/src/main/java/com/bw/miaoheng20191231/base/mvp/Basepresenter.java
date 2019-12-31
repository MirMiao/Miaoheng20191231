package com.bw.miaoheng20191231.base.mvp;

import java.lang.ref.WeakReference;

/**
 * 时间 :2019/12/31  8:48
 * 作者 :苗恒
 * 功能 :
 */
public abstract class Basepresenter<M extends IBaseModel,V extends IBaseView> {
    //声明对象
    public M model;
    public WeakReference<V> weakReference;

    public Basepresenter() {
        //初始化m层对象
        model=initModel();
    }
    public void attach(V v){
        //使用弱引用
        weakReference=new WeakReference<>(v);
    }
    public void  deattach(){
        //解绑,解决内存泄露
        if (weakReference != null) {
            weakReference.clear();
            weakReference=null;
        }
    }
    public V getView(){
        //对外提供暴露方法
        return weakReference.get();
    }

    protected abstract M initModel();
}
