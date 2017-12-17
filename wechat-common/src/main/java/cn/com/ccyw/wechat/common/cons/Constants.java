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
}
