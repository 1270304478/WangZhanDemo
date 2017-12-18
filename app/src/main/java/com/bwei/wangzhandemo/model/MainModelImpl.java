package com.bwei.wangzhandemo.model;



import com.bwei.wangzhandemo.bean.GuanBean;
import com.bwei.wangzhandemo.utils.MyAppliction;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainModelImpl {


    /**
     * get 请求
     * @param callBack
     */
    public void getData(final ModelCallBack callBack){

       Call<GuanBean> call =  MyAppliction.iGetDataBase.get();

        call.enqueue(new Callback<GuanBean>() {
            @Override
            public void onResponse(Call<GuanBean> call, Response<GuanBean> response) {
                GuanBean bean =response.body();
                callBack.onSuccess(bean);
            }
            @Override
            public void onFailure(Call<GuanBean> call, Throwable t) {
                callBack.onFailure(new Exception(""));
            }
        });

    }

}
