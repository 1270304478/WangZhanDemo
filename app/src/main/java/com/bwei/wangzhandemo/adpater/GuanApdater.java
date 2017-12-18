package com.bwei.wangzhandemo.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.wangzhandemo.R;
import com.bwei.wangzhandemo.bean.GuanBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/16 15:22
 */
public class GuanApdater extends RecyclerView.Adapter<GuanApdater.IViewHolder> {
    Context context;
    List<GuanBean.ResultsBean> list;

    public GuanApdater(Context context) {
        this.context = context;
    }

    public void addData(GuanBean bean) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.addAll(bean.getResults());
        notifyDataSetChanged();
    }

    @Override
    public GuanApdater.IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.myguanadapter_list_item02, null);
        return new IViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GuanApdater.IViewHolder holder, int position) {
        holder.adapterSimpledraweeview.setImageURI(list.get(position).getUrl());
        holder.adapterTextview.setText(list.get(position).getCreatedAt());
    }

    @Override
    public int getItemCount() {

     return list == null ? 0 : list.size();
    }

    static class IViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.adapter_simpledraweeview)
        SimpleDraweeView adapterSimpledraweeview;
        @BindView(R.id.adapter_textview)
        TextView adapterTextview;

        IViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
