package cn.com.ccyw.wechat.manager.service.core.impl;

import cn.com.ccyw.wechat.common.cons.Constants;
import cn.com.ccyw.wechat.common.utils.DateUtil;
import cn.com.ccyw.wechat.manager.dao.core.MessageDao;
import cn.com.ccyw.wechat.manager.dao.timer.WeatherTaskDao;
import cn.com.ccyw.wechat.manager.entity.core.json.Httpstatus;
import cn.com.ccyw.wechat.manager.entity.core.response.TextMessage;
import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherData;
import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherDatatitle;
import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherHttpstatus;
import cn.com.ccyw.wechat.manager.service.core.MessageService;
import cn.com.ccyw.wechat.manager.service.weather.YxCcywWeatherDataService;
import cn.com.ccyw.wechat.manager.service.weather.YxCcywWeatherDatatitleService;
import cn.com.ccyw.wechat.manager.service.weather.YxCcywWeatherHttpstatusService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.*;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.service.core.impl
 * @description: TODO
 * @date 2018/1/1 11:03
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;
    @Autowired
    private WeatherTaskDao weatherTaskDao;
    @Autowired
    private YxCcywWeatherHttpstatusService yxCcywWeatherHttpstatusService;
    @Autowired
    private YxCcywWeatherDatatitleService yxCcywWeatherDatatitleService;
    @Autowired
    private YxCcywWeatherDataService yxCcywWeatherDataService;

    /**
     * 解析微信发来的请求(XML)
     *
     * @param request
     * @return
     */
    @Override
    public Map<String, String> paresXml(HttpServletRequest request) {
        //将解析结果储存在HashMap中
        Map<String, String> map = new HashMap<>(16);
        InputStream input = null;
        try {
            input = request.getInputStream();
            //读取输入流
            SAXReader reader = new SAXReader();
            Document doc = reader.read(input);
            //得到xml根文件
            Element root = doc.getRootElement();
            //得到根文件的所有子节点
            List<Element> eleList = root.elements();
            //遍历所有子节点
            for (Element e : eleList) {
                map.put(e.getName(), e.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(input);
        }
        return map;
    }

    /**
     * 获取返回的文本信息
     *
     * @param fromUserName
     * @param toUserName
     * @param content
     * @return
     */
    @Override
    public String getTextMessage(String fromUserName, String toUserName, String content) {
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(System.currentTimeMillis());
        textMessage.setMsgType(Constants.RESP_MESSAGE_TYPE_TEXT);
        if (content.endsWith(Constants.REQ_MESSAGE_SUFFIX)) {
            String city = StringUtils.substring(content, 0, StringUtils.lastIndexOf(content, Constants.REQ_MESSAGE_SUFFIX));
            textMessage.setContent(getWeatherContent(city));
        } else {
            textMessage.setContent(content);
        }
        return messageDao.textMessageToXml(textMessage);
    }

    /**
     * 获取格式化的天气信息
     *
     * @param city
     * @return
     */
    @Override
    public String getWeatherContent(String city) {
        String content = "【" + city + "】实时天气数据获取失败！";
        YxCcywWeatherHttpstatus yxCcywWeatherHttpstatus = new YxCcywWeatherHttpstatus();
        yxCcywWeatherHttpstatus.setDate(DateUtil.formatDate(new Date()));
        yxCcywWeatherHttpstatus.setCity(city);
        YxCcywWeatherHttpstatus httpstatus = yxCcywWeatherHttpstatusService.selectByEntitySelective(yxCcywWeatherHttpstatus);
        if (Objects.nonNull(httpstatus)) {
            YxCcywWeatherDatatitle datatitle = yxCcywWeatherDatatitleService.selectByStatusId(httpstatus.getStatusid());
            List<YxCcywWeatherData> datas = yxCcywWeatherDataService.selectByStatusId(httpstatus.getStatusid());
            content = messageDao.formatWeatherData(httpstatus, datatitle, datas);
        } else {
            Httpstatus status = weatherTaskDao.getWeatherData(city);
            httpstatus = weatherTaskDao.getHttpstatus(status);
            if (Objects.nonNull(httpstatus)) {
                LOGGER.debug("通过Http请求获取天气数据成功！");
                YxCcywWeatherDatatitle datatitle = weatherTaskDao.getDatatitle(status);
                List<YxCcywWeatherData> datas = weatherTaskDao.getData(status);
                content = messageDao.formatWeatherData(httpstatus, datatitle, datas);

                //入库
                yxCcywWeatherHttpstatusService.insertSelective(httpstatus);
                yxCcywWeatherDatatitleService.insertSelective(datatitle);
                yxCcywWeatherDataService.batchInsert(datas);
            }
        }
        return content;
    }
}
