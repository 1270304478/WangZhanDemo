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

import com.bwei.wangzhandemo.AbstractUiCallBack;
import com.bwei.wangzhandemo.OkhttpUtils;
import com.bwei.wangzhandemo.R;
import com.bwei.wangzhandemo.adpater.RecyFaBuAdapter;
import com.bwei.wangzhandemo.bean.RecyBean;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/16 11:11
 */
public class FragmentManager02 extends Fragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.springview)
    SpringView springview;
    Unbinder unbinder;
    private RecyFaBuAdapter recyAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragmentmanager02_fabu, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    int page = 0;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyAdapter = new RecyFaBuAdapter(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerview.setLayoutManager(manager);
        //进入页面访问数据
        getData();
        springview.setHeader(new DefaultHeader(getActivity()));
        springview.setFooter(new DefaultFooter(getActivity()));
        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page ++;
                getData();
                springview.onFinishFreshAndLoad();
            }
            @Override
            public void onLoadmore() {
                page = 0;
                getData();
                springview.onFinishFreshAndLoad();
            }
        });
    }

    public void getData(){
        final String path = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/"+page;
       OkhttpUtils.getInstance().asy(null, path, new AbstractUiCallBack<RecyBean>() {

            @Override
            //abstractUiCallBack的接口回调
            public void success(RecyBean bean) {
                //获取数据 .调用适配器中的添加数据的方法，，刷新添加到前面
                recyAdapter.addData(bean.getResults());
                recyclerview.setAdapter(recyAdapter);
            }

            @Override
            public void failure(Exception e) {
                Toast.makeText(getActivity(),"e:"+e,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
