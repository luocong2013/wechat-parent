package cn.com.ccyw.wechat.manager.dao.core;

import cn.com.ccyw.wechat.common.cons.Constants;
import cn.com.ccyw.wechat.common.utils.HttpClientUtil;
import cn.com.ccyw.wechat.common.utils.JsonUtil;
import cn.com.ccyw.wechat.manager.entity.core.json.Data;
import cn.com.ccyw.wechat.manager.entity.core.json.Datatitle;
import cn.com.ccyw.wechat.manager.entity.core.json.Httpstatus;
import cn.com.ccyw.wechat.manager.entity.core.response.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.dao.core
 * @description: TODO
 * @date 2017/12/24 19:44
 */
@Component
public class MessageDao {
    /**
     * 解析微信发来的请求(XML)
     */
    public Map<String, String> paresXml(HttpServletRequest req) {
        //将解析结果储存在HashMap中
        Map<String, String> map = new HashMap<>(16);
        InputStream input = null;
        try {
            input = req.getInputStream();
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
    public String getTextMessage(String fromUserName, String toUserName, String content) {
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(System.currentTimeMillis());
        textMessage.setMsgType(Constants.RESP_MESSAGE_TYPE_TEXT);
        if (content.endsWith(Constants.REQ_MESSAGE_SUFFIX)) {
            String city = StringUtils.substring(content, 0, StringUtils.lastIndexOf(content, Constants.REQ_MESSAGE_SUFFIX));
            textMessage.setContent(formatWeatherData(city));
        } else {
            textMessage.setContent(content);
        }
        return textMessageToXml(textMessage);
    }


    /**
     * 获取天气数据
     *
     * @param city
     * @return
     */
    public Httpstatus getWeatherData(String city) {
        Httpstatus httpstatus = null;
        String json = HttpClientUtil.sendGet(city);
        if (StringUtils.isNotBlank(json)) {
            httpstatus = JsonUtil.json2Bean(json, Httpstatus.class);
        }
        return httpstatus;
    }

    /**
     * 格式化天气数据为一段文本形式
     *
     * @param city
     * @return
     */
    private String formatWeatherData(String city) {
        StringBuilder builder = new StringBuilder();
        Httpstatus httpstatus = getWeatherData(city);
        Datatitle datatitle = httpstatus.getData();
        if (httpstatus != null && datatitle != null) {
            builder.append("----【" + city + "】当前天气情况----").append(StringUtils.LF);
            builder.append("当前湿度：" + datatitle.getShidu()).append(StringUtils.LF);
            builder.append("当前PM2.5浓度：" + datatitle.getPm25()).append(StringUtils.LF);
            builder.append("当前PM10浓度：" + datatitle.getPm10()).append(StringUtils.LF);
            builder.append("当前空气质量：" + datatitle.getQuality()).append(StringUtils.LF);
            builder.append("当前温度：" + datatitle.getWendu()).append(StringUtils.LF);
            builder.append("*******温馨提示*******").append(StringUtils.LF);
            builder.append(datatitle.getGanmao()).append(StringUtils.LF);

            Data yesterday = datatitle.getYesterday();
            builder.append("----【" + city + "】昨天天气情况----").append(StringUtils.LF);
            builder.append("******" + yesterday.getDate() + "******").append(StringUtils.LF);
            builder.append("日出时间：" + yesterday.getSunrise()).append(StringUtils.LF);
            builder.append("日落时间：" + yesterday.getSunset()).append(StringUtils.LF);
            builder.append("最高温度：" + yesterday.getHigh()).append(StringUtils.LF);
            builder.append("最低温度：" + yesterday.getLow()).append(StringUtils.LF);
            builder.append("空气质量指数：" + yesterday.getAqi()).append(StringUtils.LF);
            builder.append("风向：" + yesterday.getFx()).append(StringUtils.LF);
            builder.append("风力：" + yesterday.getFl()).append(StringUtils.LF);
            builder.append("天气：" + yesterday.getType()).append(StringUtils.LF);
            builder.append("提示：" + yesterday.getNotice()).append(StringUtils.LF);

            List<Data> datas = datatitle.getForecast();
            builder.append("----【" + city + "】最近天气情况----").append(StringUtils.LF);
            for (Data data : datas) {
                builder.append("******" + data.getDate() + "******").append(StringUtils.LF);
                builder.append("日出时间：" + data.getSunrise()).append(StringUtils.LF);
                builder.append("日落时间：" + data.getSunset()).append(StringUtils.LF);
                builder.append("最高温度：" + data.getHigh()).append(StringUtils.LF);
                builder.append("最低温度：" + data.getLow()).append(StringUtils.LF);
                builder.append("空气质量指数：" + data.getAqi()).append(StringUtils.LF);
                builder.append("风向：" + data.getFx()).append(StringUtils.LF);
                builder.append("风力：" + data.getFl()).append(StringUtils.LF);
                builder.append("天气：" + data.getType()).append(StringUtils.LF);
                builder.append("提示：" + data.getNotice()).append(StringUtils.LF);
            }
        } else {
            builder.append("【" + city + "】实时天气数据获取失败！");
        }
        return builder.toString();
    }

    /**
     * 文本消息对象转换成XML
     */
    private String textMessageToXml(TextMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 图片消息对象转换成XML
     */
    private String imageMessageToXml(ImageMessage imageMessage) {
        xstream.alias("xml", imageMessage.getClass());
        xstream.alias("", Image.class);
        return xstream.toXML(imageMessage);
    }

    /**
     * 语音消息对象转换成XML
     */
    private String voiceMessageToXml(VoiceMessage voiceMessage) {
        xstream.alias("xml", voiceMessage.getClass());
        xstream.alias("", Voice.class);
        return xstream.toXML(voiceMessage);
    }

    /**
     * 视频消息对象转换成XML
     */
    private String videoMessageToXml(VideoMessage videoMessage) {
        xstream.alias("xml", videoMessage.getClass());
        xstream.alias("", Video.class);
        return xstream.toXML(videoMessage);
    }

    /**
     * 音乐消息对象转换成XML
     */
    private String musicMessageToXml(MusicMessage musicMessage) {
        xstream.alias("xml", musicMessage.getClass());
        xstream.alias("", Music.class);
        return xstream.toXML(musicMessage);
    }

    /**
     * 图文消息对象转换成XML
     */
    private String newsMessageToXml(NewsMessage newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", Article.class);
        return xstream.toXML(newsMessage);
    }

    /**
     * 扩展xstream，使其支持CDATA块
     */
    private XStream xstream = new XStream(new XppDriver() {
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                //对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @Override
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }

        ;
    });
}
