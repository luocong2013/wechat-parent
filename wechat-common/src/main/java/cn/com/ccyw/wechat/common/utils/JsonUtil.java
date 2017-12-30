package cn.com.ccyw.wechat.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.common.utils
 * @description: json工具类
 * @date 2017/12/25 20:27
 */
public class JsonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * json字符串转换为javabean
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T json2Bean(String json, Class<T> clazz) {
        T t = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            t = mapper.readValue(json, clazz);
        } catch (IOException e) {
            LOGGER.error("json字符串转换出错！", e);
        }
        return t;
    }

    /**
     * javabean转换为json
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String bean2Json(T t) {
        String json = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
