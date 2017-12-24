package cn.com.ccyw.wechat.manager.entity.core.response;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.entity.core.response
 * @description: TODO
 * @date 2017/12/24 19:02
 */
public class MusicMessage extends BaseMessage {
    //音乐
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}
