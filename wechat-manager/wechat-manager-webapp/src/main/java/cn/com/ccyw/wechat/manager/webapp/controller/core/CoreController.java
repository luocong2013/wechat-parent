package cn.com.ccyw.wechat.manager.webapp.controller.core;

import cn.com.ccyw.wechat.manager.service.core.CoreService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.webapp.controller.core
 * @description: 微信统一入口控制器
 * @date 2017/12/24 16:48
 */
@Controller
@RequestMapping(value = "/coreController")
public class CoreController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoreController.class);


    @Autowired
    private CoreService coreService;

    /**
     * 接入微信
     * @param reqest
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/connect", method = {RequestMethod.GET, RequestMethod.POST})
    public void connect(HttpServletRequest reqest, HttpServletResponse response) {
        PrintWriter writer = null;
        try {
            //微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码
            reqest.setCharacterEncoding("UTF-8");
            //在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上
            response.setCharacterEncoding("UTF-8");
            //是否是GET请求
            boolean isGet = reqest.getMethod().toUpperCase().equals(HttpMethod.GET.name());
            writer = response.getWriter();

            if (isGet) {
                String echostr = reqest.getParameter("echostr");
                String signature = reqest.getParameter("signature");
                String timestamp = reqest.getParameter("timestamp");
                String nonce = reqest.getParameter("nonce");
                if (coreService.checkSignature(signature, timestamp, nonce)) {
                    writer.write(echostr);
                    LOGGER.info("成功接入微信");
                } else {
                    LOGGER.error("接入微信失败");
                }
            } else {
                String respMsg = coreService.processRequest(reqest);
                writer.write(respMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            IOUtils.closeQuietly(writer);
        }
    }


}
