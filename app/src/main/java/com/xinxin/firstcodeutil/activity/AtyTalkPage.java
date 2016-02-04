package com.xinxin.firstcodeutil.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;
import com.xinxin.firstcodeutil.adapter.TalkAdapter;
import com.xinxin.firstcodeutil.model.TalkMsg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinxin on 2016/2/4.
 * <p/>
 * 聊天页面
 */
public class AtyTalkPage extends BaseActivity {

    private ListView talkList;
    private EditText editText;
    private Button send;

    private TalkAdapter talkAdapter;
    private List<TalkMsg> talkMsgList = new ArrayList<TalkMsg>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);

        initMsg();

        talkAdapter = new TalkAdapter(this,talkMsgList);

        talkList = (ListView) findViewById(R.id.talk_listview);
        editText = (EditText) findViewById(R.id.talk_input_text);
        send = (Button) findViewById(R.id.talk_send);

        talkList.setAdapter(talkAdapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    TalkMsg talkMsg = new TalkMsg(content,TalkMsg.TYPE_SEND);
                    talkMsgList.add(talkMsg);

                    talkAdapter.notifyDataSetChanged();

                    talkList.setSelection(talkMsgList.size());

                    editText.setText("");
                }
            }
        });

    }

    public void initMsg() {
        TalkMsg talkMsg = new TalkMsg("Hello guy.",TalkMsg.TYPE_RECEIVED);
        talkMsgList.add(talkMsg);
        TalkMsg talkMsg2 = new TalkMsg("Hello. Who is that?",TalkMsg.TYPE_SEND);
        talkMsgList.add(talkMsg2);
        TalkMsg talkMsg3 = new TalkMsg("This is Tom. Nice to meet you.",TalkMsg.TYPE_RECEIVED);
        talkMsgList.add(talkMsg3);

    }
}
