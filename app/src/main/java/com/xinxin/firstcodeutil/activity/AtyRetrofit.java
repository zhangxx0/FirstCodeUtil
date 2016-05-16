package com.xinxin.firstcodeutil.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.xinxin.firstcodeutil.R;
import com.xinxin.firstcodeutil.model.KongzhongUserInfo;
import com.xinxin.firstcodeutil.network.api.TankService;
import com.xinxin.firstcodeutil.retrofit.Contributor;
import com.xinxin.firstcodeutil.retrofit.Github;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xinxin on 2016/4/19.
 * Retrofit学习
 */
public class AtyRetrofit extends AppCompatActivity implements View.OnClickListener {

    private Button get_contributors;
    private Button get_tankinfo;
    private TextView tv_show_contributors;

    public static final String API_URL = "https://api.github.com";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        initView();

        get_contributors.setOnClickListener(this);
        get_tankinfo.setOnClickListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {
        get_contributors = (Button) findViewById(R.id.get_contributors);
        get_tankinfo = (Button) findViewById(R.id.get_tankinfo);
        tv_show_contributors = (TextView) findViewById(R.id.tv_show_contributors);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_contributors:
                // 查看github上某个repo的contributors
                // https://api.github.com/repos/{owner}/{repo}/contributors
                getContributors();
                break;
            case R.id.get_tankinfo:

                getTankInfo();
                break;
            default:
                break;
        }
    }

    /**
     * 使用Retrofit获取坦克信息
     */
    private void getTankInfo() {

        String baseUrl = "http://scw.worldoftanks.cn/zh-cn/community/accounts/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TankService userService = retrofit.create(TankService.class);
        Call<KongzhongUserInfo> call = userService.getUserInfo("折腾5号","");

        call.enqueue(new Callback<KongzhongUserInfo>() {
            @Override
            public void onResponse(Call<KongzhongUserInfo> call, Response<KongzhongUserInfo> response) {
                Log.d("成功：",response.toString());
                // Response{protocol=http/1.1, code=404, message=NOT FOUND, url=http://scw.worldoftanks.cn/zh-cn/community/accounts/search/userinfo}
                // Response{protocol=http/1.1, code=404, message=NOT FOUND, url=http://scw.worldoftanks.cn/zh-cn/community/accounts/search/?name=%E6%8A%98%E8%85%BE5%E5%8F%B7&name_gt=}
                Log.d("成功：", response.body().toString());

            }

            @Override
            public void onFailure(Call<KongzhongUserInfo> call, Throwable t) {
                Log.d("失败：", String.valueOf(t));
            }
        });

    }

    private void getContributors() {
        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        Github github = retrofit.create(Github.class);
        // Create a call instance for looking up Retrofit contributors.
        Call<List<Contributor>> call = github.contributors("square", "retrofit");

        // Fetch and print a list of the contributors to the library.
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                StringBuffer sb = new StringBuffer();
                for (Contributor contributor : response.body()) {
                    System.out.println(contributor.getLogin() + " (" + contributor.getContributions() + ")");
                    sb.append(contributor.getLogin() + " (" + contributor.getContributions() + ");");
                }
                tv_show_contributors.setText(sb.toString());

            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {

            }
        });
    }



}
