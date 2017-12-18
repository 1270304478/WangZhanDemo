package com.bwei.wangzhandemo.adpater;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.wangzhandemo.R;
import com.bwei.wangzhandemo.bean.Bean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/15 18:12
 */
public class MyNewswAdapter extends RecyclerView.Adapter<MyNewswAdapter.IviewHolder> {
    Context context;
    List<Bean.RetBean.ListBean> list;

    public MyNewswAdapter(Context context, List<Bean.RetBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyNewswAdapter.IviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new IviewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyNewswAdapter.IviewHolder holder, int position) {
   holder.listImage.setImageURI(Uri.parse(list.get(position).getPic()));
   holder.tv2.setText(list.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    static class IviewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.list_image)
        SimpleDraweeView listImage;
        @BindView(R.id.tv2)
        TextView tv2;

        IviewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
