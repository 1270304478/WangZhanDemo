package com.bwei.wangzhandemo.adpater;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.wangzhandemo.R;
import com.bwei.wangzhandemo.bean.RecyBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/16 17:32
 */
public class RecyFaBuAdapter extends RecyclerView.Adapter<RecyFaBuAdapter.IviewHolder>{
    Context context;
    List<RecyBean.ResultsBean> list;
    public RecyFaBuAdapter(Context context) {
        this.context=context;

    }
    public void addData(List<RecyBean.ResultsBean> list) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
       this.list.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public RecyFaBuAdapter.IviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragmentmanager02_fabu_list_item, parent, false);
        return new IviewHolder(view);

    }
    @Override
    public void onBindViewHolder(RecyFaBuAdapter.IviewHolder holder, int position) {
                  holder.adapterSimpledraweeview.setImageURI(Uri.parse(list.get(position).getUrl()));
                  holder.adapterTextview.setText(list.get(position).getPublishedAt());
    }
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class IviewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.adapter_simpledraweeview)
        SimpleDraweeView adapterSimpledraweeview;
        @BindView(R.id.adapter_textview)
        TextView adapterTextview;

        IviewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
