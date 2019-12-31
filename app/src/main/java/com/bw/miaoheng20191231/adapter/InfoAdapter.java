package com.bw.miaoheng20191231.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.miaoheng20191231.R;
import com.bw.miaoheng20191231.emtity.InfoEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 时间 :2019/12/31  9:15
 * 作者 :苗恒
 * 功能 :
 */
public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.MyViewHolder> {
    Context context;
    List<InfoEntity.RankingBean> ranking;


    public InfoAdapter(Context context, List<InfoEntity.RankingBean> ranking) {
        this.context = context;
        this.ranking = ranking;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //渲染布局
        View view = View.inflate(context, R.layout.item, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       holder.tvRank.setText(ranking.get(position).getRank()+"");
       holder.tvName.setText(ranking.get(position).getName());
        Glide.with(context).load(ranking.get(position).getAvatar())
                .circleCrop()//原型图
                .placeholder(R.mipmap.ic_launcher)//展位图
                .error(R.mipmap.ic_launcher_round)//错误图
                .into(holder.ivImage);
        //自定义接口回调
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   adapterCallBacl.getName(ranking.get(position).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return ranking.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        //使用bindView查找控件
        @BindView(R.id.tv_rank)
        TextView tvRank;
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_name)
        TextView tvName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //绑定吧乬
            ButterKnife.bind(this,itemView);
        }
    }
    //使用接口回调
    private AdapterCallBacl adapterCallBacl;

    public void setAdapterCallBacl(AdapterCallBacl adapterCallBacl) {
        this.adapterCallBacl = adapterCallBacl;
    }

    public interface AdapterCallBacl{
        void getName(String s);
    }
}
