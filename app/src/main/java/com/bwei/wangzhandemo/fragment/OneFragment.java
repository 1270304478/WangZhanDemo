package com.bwei.wangzhandemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.wangzhandemo.MainActivity;
import com.bwei.wangzhandemo.R;
import com.zaaach.citypicker.CityPickerActivity;

import java.util.ArrayList;
import java.util.List;

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
public class OneFragment extends Fragment {
    private static final int REQUEST_CODE_PICK_CITY = 0;
    private static final int RESULT_OK = 1;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp01)
    ViewPager vp01;
    Unbinder unbinder;
    @BindView(R.id.textview_quanguo)
    TextView textviewQuanguo;
    private List<String> list01;
    List<Fragment> listfrag = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onefragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list01 = new ArrayList<>();
        list01.add("全部");
        list01.add("综艺娱乐");
        list01.add("财经访谈");
        list01.add("文化旅游");
        list01.add("时尚体育");
        list01.add("青少科教");
        list01.add("养生保健");
        list01.add("公益");
        listfrag.add(new LittleFragment());
        listfrag.add(new LittleFragment2());
        listfrag.add(new LittleFragment3());
        listfrag.add(new LittleFragment4());
        listfrag.add(new LittleFragment5());
        listfrag.add(new LittleFragment6());
        listfrag.add(new LittleFragment7());
        listfrag.add(new LittleFragment8());
        vp01.setOffscreenPageLimit(list01.size());
        vp01.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return listfrag.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return list01.get(position);
            }
            @Override
            public int getCount() {
                return list01.size();
            }
        });
        tabLayout.setupWithViewPager(vp01);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @OnClick(R.id.textview_quanguo)
    public void onViewClicked() {

        startActivityForResult(new Intent(getActivity(), CityPickerActivity.class),
                REQUEST_CODE_PICK_CITY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode ==RESULT_OK){
            if (data != null){
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                textviewQuanguo.setText("当前选择：" + city);
            }
        }
    }
}
