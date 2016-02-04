package com.xinxin.firstcodeutil.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;

/**
 * Created by xinxin on 2016/2/4.
 * <p/>
 * 强制下线登陆页面
 */
public class AtyOfflineLogin extends BaseActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_login);

        pref = PreferenceManager.getDefaultSharedPreferences(this);

        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);
        login = (Button) findViewById(R.id.login);

        boolean isRemember = pref.getBoolean("remember_password", false);

        if (isRemember) {
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");

            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                if (account.equals("admin") && password.equals("123")) {

                    editor = pref.edit();

                    if (rememberPass.isChecked()) {

                        editor.putBoolean("remember_password", true);
                        editor.putString("account", account);
                        editor.putString("password", password);
                    } else {
                        editor.clear();
                    }

                    editor.commit();

                    Intent intent = new Intent(AtyOfflineLogin.this, AtyOfflineMain.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AtyOfflineLogin.this,
                            "account or password is invalid",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
