package com.bwei.wangzhandemo.activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.bwei.wangzhandemo.R;
import com.bwei.wangzhandemo.fragment.FourFragment;
import com.bwei.wangzhandemo.fragment.OneFragment;
import com.bwei.wangzhandemo.fragment.ThreeFragment;
import com.bwei.wangzhandemo.fragment.TwoFragment;
import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;
public class OneActivity extends FragmentActivity {
    @BindView(R.id.button_tab_bar)
    BottomTabBar buttonTabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_one);
        ButterKnife.bind(this);
        buttonTabBar.init(getSupportFragmentManager())
                .setImgSize(50, 50)
                .setFontSize(15)
                .setTabPadding(4, 6, 10)
                .setChangeColor(Color.RED, Color.DKGRAY)
                .addTabItem("活动", R.drawable.aak, OneFragment.class)
                .addTabItem("发布", R.drawable.ic_goods_store, TwoFragment.class)
                .addTabItem("管理", R.drawable.aaq, ThreeFragment.class)
                .addTabItem("我的", R.drawable.afx, FourFragment.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {
                    }
                });
    }
}
