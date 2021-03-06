package cn.com.ccyw.wechat.manager.entity.core.response;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.entity.core.response
 * @description: TODO
 * @date 2017/12/24 19:00
 */
public class BaseMessage {
    //接收方账号(收到的OpenID)
    private String ToUserName;
    //开发者微信号
    private String FromUserName;
    //消息创建时间(整型)
    private long CreateTime;
    //消息类型为(text/image/voice/video/music/news)
    private String MsgType;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
