package cn.com.ccyw.utils;

import cn.com.ccyw.cons.Constants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.utils
 * @description: 时间工具类
 * @date 2017/12/16 17:35
 */
public class DateUtil {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String city = URLEncoder.encode("成都23", "utf-8");
        String url = String.format(Constants.WETHER_JSON_API_URL, city);
        System.out.println(url);
        System.out.println(HttpClientUtil.sendGet(url, "UTF-8"));
    }
}
