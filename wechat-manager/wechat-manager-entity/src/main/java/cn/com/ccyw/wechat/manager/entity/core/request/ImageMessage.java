package cn.com.ccyw.wechat.manager.entity.core.request;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.entity.core.request
 * @description: TODO
 * @date 2017/12/24 18:56
 */
public class ImageMessage extends BaseMessage {
    //图片链接
    private String PicUrl;
    //图片消息媒体id，可以调用多媒体文件下载接口拉取数据
    private String MediaId;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
