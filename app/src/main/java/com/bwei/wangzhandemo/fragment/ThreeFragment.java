package com.bwei.wangzhandemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bwei.wangzhandemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/15 13:44
 */
public class ThreeFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.but_baoming)
    RadioButton butBaoming;
    @BindView(R.id.but_fabu)
    RadioButton butFabu;
    Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.threefragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = new FragmentManager01();
                        break;
                    case 1:
                        fragment = new FragmentManager02();
                        break;
                    default:
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
        //给group设置选中改变的监听事件
        rg.setOnCheckedChangeListener(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.but_baoming:
                Toast.makeText(getActivity(), "报名", Toast.LENGTH_SHORT).show();
                viewPager.setCurrentItem(0, false);//就不会滑动了

                break;
            case R.id.but_fabu:
                viewPager.setCurrentItem(1, false);
                Toast.makeText(getActivity(), "发布", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
