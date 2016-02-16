package com.xinxin.firstcodeutil.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;
import com.xinxin.firstcodeutil.db.MyDatabaseHelper;

/**
 * Created by xinxin on 2016/2/4.
 * <p/>
 * Android 存储
 */
public class AtyStorage extends BaseActivity implements View.OnClickListener {

    private Button storage_file_in;
    private Button storage_preferences_in;
    private Button storage_lite_init, storage_lite_add, storage_lite_del,
            storage_lite_edit, storage_lite_select;

    private MyDatabaseHelper myDatabaseHelper;

    private TextView storage_sqlite_show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        storage_file_in = (Button) findViewById(R.id.storage_file_in);

        storage_preferences_in = (Button) findViewById(R.id.storage_preferences_in);

        storage_lite_init = (Button) findViewById(R.id.storage_lite_init);
        storage_lite_add = (Button) findViewById(R.id.storage_lite_add);
        storage_lite_del = (Button) findViewById(R.id.storage_lite_del);
        storage_lite_edit = (Button) findViewById(R.id.storage_lite_edit);
        storage_lite_select = (Button) findViewById(R.id.storage_lite_select);
        storage_sqlite_show = (TextView) findViewById(R.id.storage_sqlite_show);

        storage_file_in.setOnClickListener(this);
        storage_preferences_in.setOnClickListener(this);
        storage_lite_init.setOnClickListener(this);
        storage_lite_add.setOnClickListener(this);
        storage_lite_del.setOnClickListener(this);
        storage_lite_edit.setOnClickListener(this);
        storage_lite_select.setOnClickListener(this);

        myDatabaseHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.storage_file_in:
                Intent intent = new Intent(this, AtyStorageFile.class);
                startActivity(intent);
                break;
            case R.id.storage_preferences_in:
                Intent intent1 = new Intent(this, AtyStorageSharedPreferences.class);
                startActivity(intent1);
                break;

            // 数据库的初始化与增删改查；
            case R.id.storage_lite_init:
                myDatabaseHelper.getWritableDatabase();
                break;
            case R.id.storage_lite_add:
                // 新增数据
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("name", "狼牙");
                values.put("author", "刘猛");
                values.put("pages", "500");
                values.put("price", "49");

                db.insert("Book", null, values);

                values.clear();
                values.put("name", "最后一颗子弹留给我");
                values.put("author", "刘猛");
                values.put("pages", "550");
                values.put("price", "59");

                db.insert("Book", null, values);

                break;
            case R.id.storage_lite_del:

                SQLiteDatabase db2 = myDatabaseHelper.getWritableDatabase();
                db2.delete("Book", "pages > ?", new String[]{"500"});

                break;
            case R.id.storage_lite_edit:
                SQLiteDatabase db3 = myDatabaseHelper.getWritableDatabase();

                db3.beginTransaction();

                try {
                    ContentValues values1 = new ContentValues();
                    values1.put("price", "79");
                    db3.update("Book", values1, "name = ?", new String[]{"最后一颗子弹留给我"});

                    db3.setTransactionSuccessful();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    db3.endTransaction();
                }

                break;
            case R.id.storage_lite_select:
                SQLiteDatabase db4 = myDatabaseHelper.getReadableDatabase();
                Cursor cursor = db4.query("Book", null, null, null, null, null, null);

                StringBuffer sb = new StringBuffer();

                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));

                        sb.append("Name:" + name + ",Author:" + author + ",Pages:" + pages + ",Price:" + price + "/n");

                    } while (cursor.moveToNext());
                }

                storage_sqlite_show.setText(sb.toString());

                break;
        }
    }
}
