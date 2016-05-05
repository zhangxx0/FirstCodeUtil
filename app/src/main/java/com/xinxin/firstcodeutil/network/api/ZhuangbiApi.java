package com.xinxin.firstcodeutil.network.api;

import com.xinxin.firstcodeutil.model.ZhuangbiImage;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xinxin on 2016/5/6.
 */
public interface ZhuangbiApi {
    @GET("search")
    Observable<List<ZhuangbiImage>> search(@Query("q") String query);
}
