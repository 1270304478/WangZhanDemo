package com.bwei.wangzhandemo.view;


import com.bwei.wangzhandemo.bean.GuanBean;


public interface IMainView {


    public void onSuccess(GuanBean bean);
    public void onFailure(Exception e);

}
