package com.bw.miaoheng20191231.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bw.miaoheng20191231.base.mvp.Basepresenter;
import com.bw.miaoheng20191231.base.mvp.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 时间 :2019/12/31  8:50
 * 作者 :苗恒
 * 功能 :
 */
public abstract class BaseActivity<P extends Basepresenter> extends AppCompatActivity implements IBaseView {

    private Unbinder bind;
   public P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLlayoutid());
        //绑定布局使用bk
        bind = ButterKnife.bind(this);
        presenter=initPresenter();
        if (presenter != null) {
            presenter.attach(this);
        }
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P initPresenter();

    protected abstract int bindLlayoutid();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //当对象不为空时,释放资源
        if (presenter != null) {
            presenter.deattach();
        }
        if (bind != null) {
            bind.unbind(); //释放
        }
    }
}
