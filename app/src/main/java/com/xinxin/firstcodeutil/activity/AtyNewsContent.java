package com.xinxin.firstcodeutil.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;
import com.xinxin.firstcodeutil.fragment.NewsContentFragment;

/**
 * Created by xinxin on 2016/2/4.
 * <p/>
 * 新闻内容展示页面
 */
public class AtyNewsContent extends BaseActivity {
    public static void actionStart(Context context, String newsTitle,
                                   String newsContent) {
        Intent intent = new Intent(context, AtyNewsContent.class);
        intent.putExtra("news_title", newsTitle);
        intent.putExtra("news_content", newsContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.news_content);
        String newsTitle = getIntent().getStringExtra("news_title");
        String newsContent = getIntent().getStringExtra("news_content");
        NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager()
                .findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newsTitle, newsContent);
    }
}
