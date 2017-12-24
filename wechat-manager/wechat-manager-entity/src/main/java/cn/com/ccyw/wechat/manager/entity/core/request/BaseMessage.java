package cn.com.ccyw.wechat.manager.entity.core.request;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.entity.core.request
 * @description: TODO
 * @date 2017/12/24 18:56
 */
public class BaseMessage {
    //开发者微信号
    private String ToUserName;
    //发送方账号(一个OpenID)
    private String FromUserName;
    //消息创建时间(整型)
    private long CreateTime;
    //消息类型为(text/image/voice/video/shortvideo/location/link)
    private String MsgType;
    //消息id，64位整型
    private long MsgId;

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

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }
}
