package cn.com.ccyw.wechat.common.cons;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.common.cons
 * @description: 定义常量的接口
 * @date 2017/12/16 17:20
 */
public interface Constants {

    /**
     * http获取json格式天气接口
     */
    String WETHER_JSON_API_URL = "http://www.sojson.com/open/api/weather/json.shtml?city=%s";
    /**
     * http获取xml格式天气接口
     */
    String WETHER_XML_API_URL = "http://www.sojson.com/open/api/weather/xml.shtml?city=%s";
    /**
     * 微信公众名片Token
     * 与接口配置中的Token要一致
     */
    String TOKEN = "weixinTEST";
    /**
     * 微信公众平台AppID
     */
    String APP_ID = "wxb1eb4632ddb03ed0";
    /**
     * 微信公众平台AppSecret
     */
    String APP_SECRET = "14e01b078c73fd04b15e7a1450f7a9b4";
    /**
     * 获取access_token的接口地址(GET请求)
     */
    String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    /**
     * 菜单创建接口(POST请求)
     */
    String MENU_CREATE_URL = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    /**
     * 请求消息内容匹配
     */
    String REQ_MESSAGE_SUFFIX = "天气";
    /**
     * http请求状态主键添加位数
     */
    int HTTP_STATUS_ID_WS = 4;
    /**
     * 天气数据添加位数
     */
    int DADA_ID_WS = 3;

    /*************************************************微信消息类型 start********************************************************/
    /**
     * 返回消息类型：文本
     */
    String RESP_MESSAGE_TYPE_TEXT = "text";
    /**
     * 返回消息类型：图片
     */
    String RESP_MESSAGE_TYPE_IMAGE = "image";
    /**
     * 返回消息类型：语音
     */
    String RESP_MESSAGE_TYPE_VOICE = "voice";
    /**
     * 返回消息类型：视频
     */
    String RESP_MESSAGE_TYPE_VIDEO = "video";
    /**
     * 返回消息类型：音乐
     */
    String RESP_MESSAGE_TYPE_MUSIC = "music";
    /**
     * 返回消息类型：图文
     */
    String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * 请求消息类型：文本
     */
    String REQ_MESSAGE_TYPE_TEXT = "text";
    /**
     * 请求消息类型：图片
     */
    String REQ_MESSAGE_TYPE_IMAGE = "image";
    /**
     * 请求消息类型：语音
     */
    String REQ_MESSAGE_TYPE_VOICE = "voice";
    /**
     * 请求消息类型：视频
     */
    String REQ_MESSAGE_TYPE_VIDEO = "video";
    /**
     * 请求消息类型：小视频
     */
    String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";
    /**
     * 请求消息类型：链接
     */
    String REQ_MESSAGE_TYPE_LINK = "link";
    /**
     * 请求消息类型：地理位置
     */
    String REQ_MESSAGE_TYPE_LOCATION = "location";

    /**
     * 请求消息类型：推送
     */
    String REQ_MESSAGE_TYPE_EVENT = "event";
    /**
     * 事件类型：
     * 1、subscribe(订阅)
     * 2、unsubscribe(取消订阅)
     * 3、CLICK(点击菜单拉取消息时的事件推送)
     * 4、VIEW(点击菜单跳转链接时的事件推送)
     */
    String EVENT_TYPE_SUBSCRIBE = "subscribe";
    String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    String EVENT_TYPE_CLICK = "CLICK";
    String EVENT_TYPE_VIEW = "VIEW";
    /*************************************************微信消息类型 end********************************************************/
}
