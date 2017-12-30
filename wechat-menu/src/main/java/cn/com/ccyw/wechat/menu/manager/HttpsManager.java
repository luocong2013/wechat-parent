package cn.com.ccyw.wechat.menu.manager;

import cn.com.ccyw.wechat.common.cons.Constants;
import cn.com.ccyw.wechat.common.utils.HttpClientUtil;
import cn.com.ccyw.wechat.common.utils.HttpsClientUtil;
import cn.com.ccyw.wechat.common.utils.JsonUtil;
import cn.com.ccyw.wechat.menu.pojo.AccessToken;
import cn.com.ccyw.wechat.menu.pojo.ErrorInfo;
import cn.com.ccyw.wechat.menu.pojo.Menu;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.menu.manager
 * @description: 进行Https请求
 * @date 2017/12/25 20:39
 */
public class HttpsManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpsManager.class);

    /**
     * 获取access_token
     *
     * @return
     * @throws IOException
     */
    private String getAccessToken() {
        String reqUrl = Constants.ACCESS_TOKEN_URL.replace("APPID", Constants.APP_ID).replace("APPSECRET", Constants.APP_SECRET);
        String json = HttpClientUtil.sendGet(reqUrl, "UTF-8");
        AccessToken at = JsonUtil.json2Bean(json, AccessToken.class);
        String access_token = at.getAccess_token();
        if (StringUtils.isNotBlank(access_token)) {
            LOGGER.info("获取access_token成功，凭证的有效时间为:{}秒", at.getExpires_in());
        } else {
            //获取access_token失败
            ErrorInfo errorInfo = JsonUtil.json2Bean(json, ErrorInfo.class);
            LOGGER.error("获取access_token失败，errcode:{}，errmsg:{}", errorInfo.getErrcode(), errorInfo.getErrmsg());
        }
        return access_token;
    }

    /**
     * 创建菜单
     *
     * @param menu 菜单实例
     * @return 0 表示创建成功，其他值表示创建失败
     */
    public int createMenu(Menu menu) throws UnsupportedEncodingException {
        int resultCode = -1;
        String access_token = getAccessToken();
        if (StringUtils.isNotBlank(access_token)) {
            //拼装创建菜单的url
            String reqUrl = Constants.MENU_CREATE_URL.replace("ACCESS_TOKEN", access_token);
            //将菜单对象转换成json字符串
            String jsonMenu = JsonUtil.bean2Json(menu);

            Map<String, String> header = new HashMap<>(16);
            header.put(HttpHeaders.CONNECTION, "keep-alive");
            header.put(HttpHeaders.ACCEPT, "*/*");
            header.put(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=UTF-8");
            header.put(HttpHeaders.HOST, "mp.weixin.qq.com");
            header.put("X-Requested-With", "XMLHttpRequest");
            header.put(HttpHeaders.CACHE_CONTROL, "max-age=0");
            header.put(HttpHeaders.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0)");

            String json = HttpsClientUtil.sendPostSSL(reqUrl, header, null, new StringEntity(jsonMenu, "UTF-8"));
            LOGGER.info(json);
            ErrorInfo errorInfo = JsonUtil.json2Bean(json, ErrorInfo.class);
            resultCode = errorInfo.getErrcode();
            if (resultCode != 0) {
                LOGGER.error("创建菜单失败，errcode:{}，errmsg:{}", resultCode, errorInfo.getErrmsg());
            }
        }
        return resultCode;
    }
}
