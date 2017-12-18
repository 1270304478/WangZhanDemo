package com.bwei.wangzhandemo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bwei.wangzhandemo.LoggingInterceptor;

import com.bwei.wangzhandemo.R;
import com.bwei.wangzhandemo.adpater.ImagePager;
import com.bwei.wangzhandemo.adpater.MyNewswAdapter;
import com.bwei.wangzhandemo.bean.Bean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
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
public class LittleFragment extends Fragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    Handler handler = new Handler();
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;
    private MyNewswAdapter myAdapter;


    private List<String> list2;

    private Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                int currentItem = viewPager.getCurrentItem();
                viewPager.setCurrentItem(currentItem + 1);
                handler2.sendEmptyMessageDelayed(0, 2000);
            }
        }
    };

    private List<ImageView> images;
    Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.littlefragment1_layout, null);
        unbinder = ButterKnife.bind(this, view);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //数据.....装的是path路径
        list2 = new ArrayList<>();
        list2.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505151721118&di=649c9a43aed72fbc4d99ec1a031510c6&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F015c7d574b9f8f6ac72525aee98351.jpg");
        list2.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505151956771&di=0eb6f306991d24b67a13ceb336f80102&imgtype=0&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farchive%2F00613def3f1beb7a35ae136341be2b589bc46a2d.jpg_320x200.jpg");
        list2.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505151847685&di=c7a4b5d08ec43fa629bcb690039a7629&imgtype=0&src=http%3A%2F%2Fattimg.dospy.com%2Fimg%2Fday_080625%2F20080625_2e91a10c444877e88827vri2ZKdGMvQo.jpg");
        list2.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505151825129&di=70bf74b87d8a15cb91a2d79f15ed0eaf&imgtype=0&src=http%3A%2F%2Fattimg.dospy.com%2Fimg%2Fday_081016%2F20081016_fee215664d5740e56c13E2YB8giERFEX.jpg");
        list2.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505746504&di=930c4d677a02328a142d6fa85ed14580&imgtype=jpg&er=1&src=http%3A%2F%2Fattimg.dospy.com%2Fimg%2Fday_090113%2F20090113_6ac58b42bea94f0b318e1B6BZb5lPZl5.jpg");

        //初始化小圆点
        initDoc();
        //设置适配器
        ImagePager imagePager = new ImagePager(getActivity(), list2,handler);
        //
        viewPager.setAdapter(imagePager);

        //设置ViewPager初始展示的位置
        viewPager.setCurrentItem(list2.size()*10000);


        //发送延时消息
        handler2.sendEmptyMessageDelayed(0,2000);


        //viewPager页面改变的监听事件
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //如果选中了这一页,,,当前小圆点红色,,,否则绿色
                for (int i = 0;i<images.size();i++){
                    if (i== position%images.size()){
                        images.get(i).setImageResource(R.drawable.doc_select);
                    }else {
                        images.get(i).setImageResource(R.drawable.doc_select_no);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build();
        Request request = new Request.Builder().url("http://api.svipmovie.com/front/columns/getVideoList.do?catalogId=402834815584e463015584e539330016").build();
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


                            if (myAdapter == null) {
                                myAdapter = new MyNewswAdapter(getActivity(), list);

                                recyclerview.setAdapter(myAdapter);
                            } else {
                                myAdapter.notifyDataSetChanged();
                            }
                        }
                    });

                }
            }


        });

    }

    /**
     * 初始化小圆点
     */
    private void initDoc() {
        //首先需要一个集合记录这些小圆点的图片,,,,当页面切换的时候,可以从集合中取出imageView进行显示图片的设置
        images = new ArrayList<>();
        linearLayout.removeAllViews();//清空/移除所有的view

        for (int i = 0;i<list2.size();i++){
            ImageView imageView = new ImageView(getActivity());

            if (i==0){//显示第一页,,,红的
                imageView.setImageResource(R.drawable.doc_select);
            }else {//绿的
                imageView.setImageResource(R.drawable.doc_select_no);
            }

            //添加到集合
            images.add(imageView);

            //加入到线性布局中显示
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(5,0,5,0);

            linearLayout.addView(imageView,params);

        }


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        handler2.removeMessages(0);
    }
}
