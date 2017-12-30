package cn.com.ccyw.wechat.common.utils;

import cn.com.ccyw.wechat.common.cons.Constants;
import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.common.utils
 * @description: Http客户端连接工具类
 * @date 2017/12/16 17:43
 */
public class HttpClientUtil {

    /**
     * 发送GET请求 获取天气数据
     * @param city
     * @return
     */
    public static String sendGet(String city) {
        String json = null;
        try {
            String url = String.format(Constants.WETHER_JSON_API_URL, URLEncoder.encode(city, "UTF-8"));
            json = sendGet(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 模拟GET请求
     *
     * @param url      资源地址
     * @param encoding 编码
     * @return
     * @throws IOException
     */
    public static String sendGet(String url, String encoding) {
        String body = "";

        CloseableHttpClient httpClient = null;
        try {
            // 创建httpclient对象
            httpClient = HttpClients.createDefault();
            // 创建get方式请求对象
            HttpGet httpGet = new HttpGet(url);
            // 执行请求操作，并拿到结果（同步阻塞）
            CloseableHttpResponse response = httpClient.execute(httpGet);
            // 获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, encoding);
            }
            // 消耗掉response
            EntityUtils.consume(entity);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            // 释放链接
            HttpClientUtils.closeQuietly(httpClient);
        }
        return body;
    }

    /**
     * 模拟POST请求
     *
     * @param url      资源地址
     * @param map      参数列表
     * @param encoding 编码
     * @return
     */
    public static String sendPost(String url, Map<String, String> map, String encoding) {
        String body = "";

        CloseableHttpClient httpClient = null;
        try {
            // 创建httpclient对象
            httpClient = HttpClients.createDefault();
            // 创建post方式请求对象
            HttpPost httpPost = new HttpPost(url);
            if (MapUtils.isNotEmpty(map)) {
                // 装填参数
                List<NameValuePair> nvps = new ArrayList<>();
                map.forEach((k, v) -> nvps.add(new BasicNameValuePair(k, v)));
                // 设置参数到请求对象中
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));
            }

            // 设置header信息
            // 指定报文头【Content-type】、【User-Agent】
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            // 执行请求操作，并拿到结果（同步阻塞）
            CloseableHttpResponse response = httpClient.execute(httpPost);
            // 获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, encoding);
            }
            // 消耗掉response
            EntityUtils.consume(entity);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            // 释放链接
            HttpClientUtils.closeQuietly(httpClient);
        }
        return body;
    }

}
