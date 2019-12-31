package com.bw.miaoheng20191231;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.miaoheng20191231.adapter.InfoAdapter;
import com.bw.miaoheng20191231.base.BaseActivity;
import com.bw.miaoheng20191231.contract.IContract;
import com.bw.miaoheng20191231.emtity.InfoEntity;
import com.bw.miaoheng20191231.presenter.Presenter;
import com.bw.miaoheng20191231.util.NetUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<Presenter> implements IContract.IView {

 //初始化控件
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void initData() {
        //判断是否有网络,当有网络是就请求数据,没网吐司
        if (NetUtil.getInstance().hasNet(this)) {
            presenter.getData("http://blog.zhaoliang5156.cn/api/news/ranking.json");
        }else{
            Toast.makeText(this, "当前无网络连接", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void initView() {
        //设置布局管理器
         rv.setLayoutManager(new LinearLayoutManager(this));
         //给分享文本设置单机事件  ,跳转到第二个页面
         tvShare.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
              startActivity(new Intent(MainActivity.this,Main2Activity.class));
             }
         });

    }

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLlayoutid() {
        return R.layout.activity_main;
    }

    @Override
    public void success(InfoEntity infoEntity) {
        //成功的数据
        List<InfoEntity.RankingBean> ranking = infoEntity.getRanking();
        InfoAdapter infoAdapter = new InfoAdapter(this, ranking);
        rv.setAdapter(infoAdapter);
         //使用接口回调的方式进行rec的条目单机事件
        infoAdapter.setAdapterCallBacl(new InfoAdapter.AdapterCallBacl() {
            @Override
            public void getName(String s) {

                Toast.makeText(MainActivity.this, ""+s, Toast.LENGTH_SHORT).show();
            }
        });
    }
     //失败的方法
    @Override
    public void failur(Throwable throwable) {
        Toast.makeText(this, "当前网络不佳,请稍后重试", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.tv_share)
    public void onViewClicked() {
    }
}
