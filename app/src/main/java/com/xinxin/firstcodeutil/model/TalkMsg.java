package com.xinxin.firstcodeutil.model;

/**
 * Created by xinxin on 2016/2/4.
 *
 * 聊天消息实体类
 *
 */
public class TalkMsg {

    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND=1;

    private String content;
    private int type;

    public TalkMsg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
