package com.xinxin.firstcodeutil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;

/**
 * Created by xinxin on 2016/3/24.
 * 图表使用学习
 */
public class AtyCharts extends BaseActivity {

    private Button pie_chart, pie_chart_paotuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);

        pie_chart = (Button) findViewById(R.id.pie_chart);
        pie_chart_paotuan = (Button) findViewById(R.id.pie_chart_paotuan);

        pie_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AtyCharts.this, AtyChartPie.class);
                startActivity(i);
            }
        });
        pie_chart_paotuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(AtyCharts.this, AtyChartPiePaotuan.class);
                startActivity(i2);
            }
        });
    }
}
