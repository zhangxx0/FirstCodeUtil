package com.xinxin.firstcodeutil.network.api;

import com.xinxin.firstcodeutil.model.KongzhongUserInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by xinxin on 2016/5/9.
 */
public interface TankService {
    // http://scw.worldoftanks.cn/zh-cn/community/accounts/search/?name=%E6%8A%98%E8%85%BE5%E5%8F%B7&name_gt=
    @Headers(
            "X-Requested-With: XMLHttpRequest"
    )
    @GET("search/")
    Call<KongzhongUserInfo> getUserInfo(@Query("name") String name,@Query("name_gt") String name_gt);

}
