package com.bwei.wangzhandemo;
import com.bwei.wangzhandemo.bean.Bean;
import com.bwei.wangzhandemo.bean.GuanBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/12/1 14:55
 */
//http://v.juhe.cn/toutiao/index?type="+data+"&key=2f092bd9ce76c0257052d6d3c93c11b4
//http://gank.io/api/data/福利/10/1
public interface IGetDataBase {
    @GET("api/data/福利/10/1")
    Call<GuanBean> get();


}
