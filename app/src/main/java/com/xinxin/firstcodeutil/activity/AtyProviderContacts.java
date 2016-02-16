package com.xinxin.firstcodeutil.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinxin on 2016/2/10.
 * <p/>
 * 获取通讯录信息
 */
public class AtyProviderContacts extends BaseActivity {

    private ListView provider_contacts_list;
    private ArrayAdapter<String> adapter;
    List<String> contactList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_contacts);

        provider_contacts_list = (ListView) findViewById(R.id.provider_contacts_list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactList);

        provider_contacts_list.setAdapter(adapter);

        readContacts();
    }

    // 读取通讯录
    private void readContacts() {
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            while (cursor.moveToNext()) {
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactList.add(displayName + "\n" + number);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

    }
}
