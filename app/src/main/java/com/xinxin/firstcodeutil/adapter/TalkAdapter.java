package com.xinxin.firstcodeutil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinxin.firstcodeutil.R;
import com.xinxin.firstcodeutil.model.TalkMsg;

import java.util.List;

/**
 * Created by xinxin on 2016/2/4.
 *
 * 聊天适配器
 *
 */
public class TalkAdapter extends BaseAdapter {

    private List<TalkMsg> talkMsgList;

    // 用于把布局文件转化为View对象
    private LayoutInflater inflater;

    public TalkAdapter(Context context,List<TalkMsg> talkMsgList) {
        this.talkMsgList = talkMsgList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return talkMsgList.size();
    }

    @Override
    public Object getItem(int position) {
        return talkMsgList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_talk_msg,null);

            viewHolder.leftLayout = (LinearLayout) convertView.findViewById(R.id.talk_left_layout);
            viewHolder.rightLayout = (LinearLayout) convertView.findViewById(R.id.talk_right_layout);
            viewHolder.leftMsg = (TextView) convertView.findViewById(R.id.talk_left_msg);
            viewHolder.rightMsg = (TextView) convertView.findViewById(R.id.talk_right_msg);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        TalkMsg talkMsg = talkMsgList.get(position);

        if (talkMsg.getType() == TalkMsg.TYPE_RECEIVED) {
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.leftMsg.setText(talkMsg.getContent());
        } else if(talkMsg.getType() == TalkMsg.TYPE_SEND) {
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.rightMsg.setText(talkMsg.getContent());
        }

        return convertView;
    }

    class ViewHolder {
        public LinearLayout leftLayout;
        public LinearLayout rightLayout;

        public TextView leftMsg;
        public TextView rightMsg;

    }
}
