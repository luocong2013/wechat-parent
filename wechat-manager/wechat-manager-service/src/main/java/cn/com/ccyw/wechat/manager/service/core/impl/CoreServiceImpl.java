package cn.com.ccyw.wechat.manager.service.core.impl;

import cn.com.ccyw.wechat.common.cons.Constants;
import cn.com.ccyw.wechat.manager.dao.core.Sha1Dao;
import cn.com.ccyw.wechat.manager.service.core.CoreService;
import cn.com.ccyw.wechat.manager.service.core.MessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.service.core.impl
 * @description: 验证签名 Token Service
 * @date 2017/12/24 17:28
 */
@Service
public class CoreServiceImpl implements CoreService {

    @Autowired
    private Sha1Dao sha1Dao;
    @Autowired
    private MessageService messageService;

    /**
     * 验证签名 TOKEN
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @return
     */
    @Override
    public boolean checkSignature(String signature, String timestamp, String nonce) {
        String []str = new String[]{Constants.TOKEN, timestamp, nonce};
        //将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(str);
        String bigStr = str[0]+str[1]+str[2];

        String digest = sha1Dao.getDigestOfString(bigStr.getBytes()).toLowerCase();
        return digest.equals(signature);
    }

    /**
     * 处理微信发来的请求
     * @param reqest
     * @return
     */
    @Override
    public String processRequest(HttpServletRequest reqest) {
        String repMsg = null;
        try {
            //默认返回的文本消息内容
            String respContent = "请求处理异常，请稍后尝试……";

            //xml请求解析
            Map<String, String> reqMap = messageService.paresXml(reqest);

            //发送方账号(OpenID)
            String fromUserName = reqMap.get("FromUserName");
            //公众账号
            String toUserName = reqMap.get("ToUserName");
            //消息类型
            String msgType = reqMap.get("MsgType");

            //文本消息
            if(msgType.equals(Constants.REQ_MESSAGE_TYPE_TEXT)){
                //接收到的文本内容
                String content = StringUtils.trim(reqMap.get("Content"));
                //获取返回的文本信息
                repMsg = messageService.getTextMessage(fromUserName, toUserName, content);
            }
            //事件推送
            else if(msgType.equals(Constants.REQ_MESSAGE_TYPE_EVENT)){
                //事件类型
                String eventType = reqMap.get("Event");

                if(eventType.equals(Constants.EVENT_TYPE_SUBSCRIBE)){
                    respContent = "/::)O(∩_∩)O谢谢你的关注！";
                   repMsg = messageService.getTextMessage(fromUserName, toUserName, respContent);
                }
            } else{
                //其他情况返回默认文本消息
                repMsg = messageService.getTextMessage(fromUserName, toUserName, respContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return repMsg;
    }
}
