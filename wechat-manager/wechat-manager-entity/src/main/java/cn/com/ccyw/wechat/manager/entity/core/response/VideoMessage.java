package cn.com.ccyw.wechat.manager.entity.core.response;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.entity.core.response
 * @description: TODO
 * @date 2017/12/24 19:04
 */
public class VideoMessage extends BaseMessage {
    private Video Video;

    public Video getVideo() {
        return Video;
    }

    public void setVideo(Video video) {
        Video = video;
    }
}
