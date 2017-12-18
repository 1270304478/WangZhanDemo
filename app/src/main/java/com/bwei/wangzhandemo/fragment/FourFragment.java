package com.bwei.wangzhandemo.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.wangzhandemo.R;
import com.bwei.wangzhandemo.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/15 13:44
 */
public class FourFragment extends Fragment {
    @BindView(R.id.image_login)
    ImageView imageLogin;
    Unbinder unbinder;
    @BindView(R.id.f4_tv)
    TextView f4Tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fourfragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.image_login)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        String titles = ((LoginActivity) context).getTitles();//通过强转成宿主activity，就可以获取到传递过来的数据
        f4Tv.setText(titles);
    }*/
}
