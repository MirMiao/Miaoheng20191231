package com.bw.miaoheng20191231;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.miaoheng20191231.base.BaseActivity;
import com.bw.miaoheng20191231.presenter.Presenter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends BaseActivity<Presenter> {

//初始化控件
    @BindView(R.id.btn_sendWeiXin)
    Button btnSendWeiXin;
    @BindView(R.id.btn_sendQQ)
    Button btnSendQQ;
    @BindView(R.id.iv_name)
    ImageView ivName;
    @Override
    protected void initData() {
        //根据自己的姓名生成一个二维码
        Bitmap bitmap = CodeUtils.createImage("苗恒", 300, 300, null);
        //设置图片
        ivName.setImageBitmap(bitmap);
    }

    @Override
    protected void initView() {
          //初始化二维码
        CodeUtils.init(this);
        //初始化enentbus
        EventBus.getDefault().register(this);
        //给二维码设置长按事件,吐司识别到的信息
        ivName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                CodeUtils.analyzeByImageView(ivName, new CodeUtils.AnalyzeCallback() {
                    //识别成功的方法
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(Main2Activity.this, ""+result, Toast.LENGTH_SHORT).show();
                    }
                    //识别失败的方法
                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(Main2Activity.this, "解析失败", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
        });
    }

    @Override
    protected Presenter initPresenter() {
        return null;
    }

    @Override
    protected int bindLlayoutid() {
        return R.layout.activity_main2;
    }


    @OnClick({R.id.btn_sendWeiXin, R.id.btn_sendQQ})
    //给微信qq设置点击事件
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sendWeiXin:
                //发送消息微信
                EventBus.getDefault().post("微信");
                break;
            case R.id.btn_sendQQ:
                //发送消息qq
                EventBus.getDefault().post("QQ");
                break;
        }
    }
     //接收微信消息
    @Subscribe
    public void getWeixin(String s){
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
    }
    //接收qq消息
    @Subscribe
    public void getQQ(String s){
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
    }


}
