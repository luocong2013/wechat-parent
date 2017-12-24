package cn.com.ccyw.wechat.manager.dao.core;

import cn.com.ccyw.wechat.manager.entity.core.response.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.commons.io.IOUtils;
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
    public Map<String, String> paresXml(HttpServletRequest req){
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
            for (Element e : eleList){
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
     * 文本消息对象转换成XML
     */
    public String textMessageToXml(TextMessage textMessage){
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }
    /**
     * 图片消息对象转换成XML
     */
    public String imageMessageToXml(ImageMessage imageMessage){
        xstream.alias("xml", imageMessage.getClass());
        xstream.alias("", Image.class);
        return xstream.toXML(imageMessage);
    }
    /**
     * 语音消息对象转换成XML
     */
    public String voiceMessageToXml(VoiceMessage voiceMessage){
        xstream.alias("xml", voiceMessage.getClass());
        xstream.alias("", Voice.class);
        return xstream.toXML(voiceMessage);
    }
    /**
     * 视频消息对象转换成XML
     */
    public String videoMessageToXml(VideoMessage videoMessage){
        xstream.alias("xml", videoMessage.getClass());
        xstream.alias("", Video.class);
        return xstream.toXML(videoMessage);
    }
    /**
     * 音乐消息对象转换成XML
     */
    public String musicMessageToXml(MusicMessage musicMessage){
        xstream.alias("xml", musicMessage.getClass());
        xstream.alias("", Music.class);
        return xstream.toXML(musicMessage);
    }
    /**
     * 图文消息对象转换成XML
     */
    public String newsMessageToXml(NewsMessage newsMessage){
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", Article.class);
        return xstream.toXML(newsMessage);
    }

    /**
     * 扩展xstream，使其支持CDATA块
     */
    private XStream xstream = new XStream(new XppDriver(){
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out){
                //对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @Override
                public void startNode(String name, Class clazz){
                    super.startNode(name, clazz);
                }

                @Override
                protected void writeText(QuickWriter writer, String text){
                    if (cdata){
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }else{
                        writer.write(text);
                    }
                }
            };
        };
    });
}
