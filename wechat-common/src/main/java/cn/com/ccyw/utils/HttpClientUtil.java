package cn.com.ccyw.utils;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.utils
 * @description: Http客户端连接工具类
 * @date 2017/12/16 17:43
 */
public class HttpClientUtil {

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

        // 创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建get方式请求对象
        HttpGet httpGet = new HttpGet(url);
        // 响应结果
        CloseableHttpResponse response = null;
        try {
            // 执行请求操作，并拿到结果（同步阻塞）
            response = client.execute(httpGet);
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
            IOUtils.closeQuietly(response);
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

        // 创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        // 响应结果
        CloseableHttpResponse response = null;
        // 装填参数
        List<NameValuePair> nvps = new ArrayList<>();
        if (map != null) {
            map.forEach((k, v) -> nvps.add(new BasicNameValuePair(k, v)));
        }

        try {
            // 设置参数到请求对象中
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

            // 设置header信息
            // 指定报文头【Content-type】、【User-Agent】
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            // 执行请求操作，并拿到结果（同步阻塞）
            response = client.execute(httpPost);
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
            IOUtils.closeQuietly(response);
        }
        return body;
    }

}
