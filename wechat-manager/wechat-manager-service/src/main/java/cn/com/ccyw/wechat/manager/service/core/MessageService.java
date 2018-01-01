package cn.com.ccyw.wechat.manager.service.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.service.core
 * @description: 微信消息处理业务层
 * @date 2018/1/1 10:58
 */
public interface MessageService {
    Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    /**
     * 解析微信发来的请求(XML)
     *
     * @param request
     * @return
     */
    Map<String, String> paresXml(HttpServletRequest request);

    /**
     * 获取返回的文本信息
     *
     * @param fromUserName
     * @param toUserName
     * @param content
     * @return
     */
    String getTextMessage(String fromUserName, String toUserName, String content);

    /**
     * 获取格式化的天气信息
     * @param city
     * @return
     */
    String getWeatherContent(String city);
}
