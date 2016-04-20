package com.xinxin.firstcodeutil.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by xinxin on 2016/4/19.
 * https://api.github.com/repos/{owner}/{repo}/contributors
 */
public interface Github {

    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(@Path("owner") String owner, @Path("repo") String repo);
}
