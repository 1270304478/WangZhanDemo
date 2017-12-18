package com.bwei.wangzhandemo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwei.wangzhandemo.LoggingInterceptor;
import com.bwei.wangzhandemo.R;
import com.bwei.wangzhandemo.adpater.MyNewswAdapter;
import com.bwei.wangzhandemo.bean.Bean;
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
 * @date: 2017/12/15 19:54
 */
public class LittleFragment7 extends Fragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    Unbinder unbinder;
    Handler handler = new Handler();
    private MyNewswAdapter myAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.littlefragment7_layout, null);
        unbinder = ButterKnife.bind(this, view);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build();
        Request request = new Request.Builder().url("http://api.svipmovie.com/front/columns/getVideoList.do?catalogId=402834815584e463015584e539330016&id=2").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    String s = response.body().string();
                    Bean bean = new Gson().fromJson(s, Bean.class);



                    final List<Bean.RetBean.ListBean> list = bean.getRet().getList();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recyclerview.setAdapter(new MyNewswAdapter(getActivity(), list));


                            if (myAdapter==null){
                                myAdapter = new MyNewswAdapter(getActivity(),list);

                                recyclerview.setAdapter(myAdapter);
                            }else {
                                myAdapter.notifyDataSetChanged();
                            }
                        }
                    });

                }
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
