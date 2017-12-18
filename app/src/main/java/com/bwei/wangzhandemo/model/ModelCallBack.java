package com.bwei.wangzhandemo.model;


import com.bwei.wangzhandemo.bean.GuanBean;



public interface ModelCallBack {

    public void onSuccess(GuanBean bean);
    public void onFailure(Exception e);

}
