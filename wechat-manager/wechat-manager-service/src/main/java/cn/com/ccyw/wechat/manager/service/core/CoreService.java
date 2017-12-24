package cn.com.ccyw.wechat.manager.service.core;

import javax.servlet.http.HttpServletRequest;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.service.core
 * @description: 验证签名 Token Service
 * @date 2017/12/24 17:23
 */
public interface CoreService {

    /**
     * 验证签名 TOKEN
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @return
     */
    boolean checkSignature(String signature, String timestamp, String nonce);

    /**
     * 处理微信发来的请求
     * @param reqest
     * @return
     */
    String processRequest(HttpServletRequest reqest);
}
