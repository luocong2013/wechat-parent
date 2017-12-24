package cn.com.ccyw.wechat.manager.entity.core.request;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.entity.core.request
 * @description: TODO
 * @date 2017/12/24 18:58
 */
public class TextMessage extends BaseMessage {
    //文本消息类容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
