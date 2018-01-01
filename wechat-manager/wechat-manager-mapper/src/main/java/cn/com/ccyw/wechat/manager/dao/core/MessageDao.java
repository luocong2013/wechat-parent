package cn.com.ccyw.wechat.manager.dao.core;

import cn.com.ccyw.wechat.common.utils.DateUtil;
import cn.com.ccyw.wechat.manager.entity.core.response.*;
import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherData;
import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherDatatitle;
import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherHttpstatus;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.Writer;
import java.util.Date;
import java.util.List;

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
     * 格式化天气数据为一段文本形式
     *
     * @param httpstatus
     * @param datatitle
     * @param datas
     * @return
     */
    public String formatWeatherData(YxCcywWeatherHttpstatus httpstatus, YxCcywWeatherDatatitle datatitle, List<YxCcywWeatherData> datas) {
        StringBuilder builder = new StringBuilder();
        builder.append("-----【" + httpstatus.getCity() + "】当前天气情况-----").append(StringUtils.LF);
        builder.append("当前湿度：" + datatitle.getShidu()).append(StringUtils.LF);
        builder.append("当前PM2.5浓度：" + datatitle.getPm25()).append(StringUtils.LF);
        builder.append("当前PM10浓度：" + datatitle.getPm10()).append(StringUtils.LF);
        builder.append("当前空气质量：" + datatitle.getQuality()).append(StringUtils.LF);
        builder.append("当前温度：" + datatitle.getWendu()).append(StringUtils.LF);
        builder.append("*******温馨提示*******").append(StringUtils.LF);
        builder.append(datatitle.getGanmao()).append(StringUtils.LF);

        final String prefix = DateUtil.formatDate(new Date(), "dd日");
        YxCcywWeatherData today = datas.stream().filter(item -> item.getDate().startsWith(prefix)).findFirst().get();
        builder.append("----【" + httpstatus.getCity() + "】今天全天天气情况----").append(StringUtils.LF);
        builder.append("******" + today.getDate() + "******").append(StringUtils.LF);
        builder.append("日出时间：" + today.getSunrise()).append(StringUtils.LF);
        builder.append("日落时间：" + today.getSunset()).append(StringUtils.LF);
        builder.append("最高温度：" + today.getHigh()).append(StringUtils.LF);
        builder.append("最低温度：" + today.getLow()).append(StringUtils.LF);
        builder.append("空气质量指数：" + today.getAqi()).append(StringUtils.LF);
        builder.append("风向：" + today.getFx()).append(StringUtils.LF);
        builder.append("风力：" + today.getFl()).append(StringUtils.LF);
        builder.append("天气：" + today.getType()).append(StringUtils.LF);
        builder.append("温馨提示：" + today.getNotice());
        return builder.toString();
    }

    /**
     * 文本消息对象转换成XML
     */
    public String textMessageToXml(TextMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 图片消息对象转换成XML
     */
    public String imageMessageToXml(ImageMessage imageMessage) {
        xstream.alias("xml", imageMessage.getClass());
        xstream.alias("", Image.class);
        return xstream.toXML(imageMessage);
    }

    /**
     * 语音消息对象转换成XML
     */
    public String voiceMessageToXml(VoiceMessage voiceMessage) {
        xstream.alias("xml", voiceMessage.getClass());
        xstream.alias("", Voice.class);
        return xstream.toXML(voiceMessage);
    }

    /**
     * 视频消息对象转换成XML
     */
    public String videoMessageToXml(VideoMessage videoMessage) {
        xstream.alias("xml", videoMessage.getClass());
        xstream.alias("", Video.class);
        return xstream.toXML(videoMessage);
    }

    /**
     * 音乐消息对象转换成XML
     */
    public String musicMessageToXml(MusicMessage musicMessage) {
        xstream.alias("xml", musicMessage.getClass());
        xstream.alias("", Music.class);
        return xstream.toXML(musicMessage);
    }

    /**
     * 图文消息对象转换成XML
     */
    public String newsMessageToXml(NewsMessage newsMessage) {
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
