package com.xinxin.firstcodeutil.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;
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
public class AtyRetrofit extends BaseActivity implements View.OnClickListener {

    private Button get_contributors;
    private TextView tv_show_contributors;

    public static final String API_URL = "https://api.github.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        initView();

        get_contributors.setOnClickListener(this);

    }

    private void initView() {
        get_contributors = (Button) findViewById(R.id.get_contributors);
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
            default:
                break;
        }
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
