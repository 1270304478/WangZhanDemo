package com.bwei.wangzhandemo.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwei.wangzhandemo.MainActivity;
import com.bwei.wangzhandemo.R;
import com.bwei.wangzhandemo.adpater.MyAdapter;
import com.bwei.wangzhandemo.adpater.MyGuanAdapter;
import com.bwei.wangzhandemo.bean.ImageBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/16 11:22
 */
public class LittleFragmentManager01 extends Fragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    Unbinder unbinder;
    private MyGuanAdapter myAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.littlefragmentmanager_buju, null);
        unbinder = ButterKnife.bind(this, view);
        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData();
    }

    private void getData() {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://www.zhaoapi.cn/product/getProducts?pscid=1")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {


            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "没有数据", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                getActivity().runOnUiThread(new Runnable() {


                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        ImageBean jsonBean = null;
                        jsonBean = gson.fromJson(json, ImageBean.class);
                        List<ImageBean.DataBean> list = jsonBean.getData();
                        if (myAdapter == null) {
                            myAdapter = new MyGuanAdapter(getActivity(), list);
                            linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            recyclerview.setLayoutManager(linearLayoutManager);

                            recyclerview.setAdapter(myAdapter);
                        } else {
                            myAdapter.notifyDataSetChanged();
                        }

                    }
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
