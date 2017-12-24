package cn.com.ccyw.wechat.manager.service.core.impl;

import cn.com.ccyw.wechat.common.cons.Constants;
import cn.com.ccyw.wechat.manager.dao.core.MessageDao;
import cn.com.ccyw.wechat.manager.dao.core.Sha1Dao;
import cn.com.ccyw.wechat.manager.entity.core.response.Article;
import cn.com.ccyw.wechat.manager.entity.core.response.NewsMessage;
import cn.com.ccyw.wechat.manager.entity.core.response.TextMessage;
import cn.com.ccyw.wechat.manager.service.core.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private MessageDao messageDao;

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
            String respContent = "请求处理异常，请稍后尝试...";

            //xml请求解析
            Map<String, String> reqMap = messageDao.paresXml(reqest);

            //发送方账号(OpenID)
            String fromUserName = reqMap.get("FromUserName");
            //公众账号
            String toUserName = reqMap.get("ToUserName");
            //消息类型
            String msgType = reqMap.get("MsgType");

            //回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(System.currentTimeMillis());
            textMessage.setMsgType(Constants.RESP_MESSAGE_TYPE_TEXT);

            if(msgType.equals(Constants.REQ_MESSAGE_TYPE_TEXT)){
                String content = reqMap.get("Content");

                respContent = content;
                textMessage.setContent(respContent);
                repMsg = messageDao.textMessageToXml(textMessage);
            }
            //事件推送
            else if(msgType.equals(Constants.REQ_MESSAGE_TYPE_EVENT)){
                //事件类型
                String eventType = reqMap.get("Event");

                if(eventType.equals(Constants.EVENT_TYPE_SUBSCRIBE)){
                    respContent = "/::)O(∩_∩)O谢谢你的关注！";

                    textMessage.setContent(respContent);
                    repMsg = messageDao.textMessageToXml(textMessage);
                } else if(eventType.equals(Constants.EVENT_TYPE_CLICK)){
                    //返回图文消息
                    NewsMessage nm = new NewsMessage();
                    nm.setToUserName(fromUserName);
                    nm.setFromUserName(toUserName);
                    nm.setCreateTime(System.currentTimeMillis());
                    nm.setMsgType(Constants.RESP_MESSAGE_TYPE_NEWS);

                    List<Article> listarticle = new ArrayList<>();

                    //点击菜单拉取消息时的事件推送
                    //事件Key值，与创建自定义菜单时的key值相等
                    String eventKey = reqMap.get("EventKey");
                    if(eventKey.equals("kbn11")){
                        //关于我们
                        Article article1 = new Article();
                        article1.setTitle("关于我们");
                        article1.setDescription("很值得加盟的赚钱项目——“江南南方馄饨王”");
                        article1.setPicUrl("http://123.56.113.159/javatext2013/images/old/a1.jpg");
                        article1.setUrl("http://123.56.113.159/javatext2013/aboutme.html");

                        listarticle.add(article1);
                    } else if(eventKey.equals("kbn21")){
                        //加盟故事

                    } else if(eventKey.equals("kbn23")){
                        //旗下店面

                    } else if(eventKey.equals("kbn32")){
                        //最新优惠
                    }

                    nm.setArticleCount(listarticle.size());
                    nm.setArticles(listarticle);
                    repMsg = messageDao.newsMessageToXml(nm);
                }
            } else{
                //其他情况返回默认文本消息
                textMessage.setContent(respContent);
                repMsg = messageDao.textMessageToXml(textMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return repMsg;
    }
}
