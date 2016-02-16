package com.xinxin.firstcodeutil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;

/**
 * Created by xinxin on 2016/2/10.
 *
 * ContentProvider
 *
 */
public class AtyProvider extends BaseActivity {

    private Button provider_contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        provider_contacts = (Button) findViewById(R.id.provider_contacts);

        provider_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(AtyProvider.this,AtyProviderContacts.class);
                startActivity(i);

            }
        });

    }
}
