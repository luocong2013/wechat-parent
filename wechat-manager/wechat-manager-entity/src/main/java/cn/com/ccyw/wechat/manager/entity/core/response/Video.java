package cn.com.ccyw.wechat.manager.entity.core.response;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.entity.core.response
 * @description: TODO
 * @date 2017/12/24 19:04
 */
public class Video {
    //通过素材管理接口上传多媒体文件，得到的id
    private String MediaId;
    //视频消息的标题
    private String Title;
    //视频消息的描述
    private String Description;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
