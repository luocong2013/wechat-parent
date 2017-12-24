package cn.com.ccyw.wechat.manager.entity.core.request;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.entity.core.request
 * @description: TODO
 * @date 2017/12/24 18:59
 */
public class VoiceMessage extends BaseMessage {
    //语音消息媒体id，可以调用多媒体文件下载接口拉取数据
    private String MediaId;
    //语音格式，如amr，speex等
    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}
