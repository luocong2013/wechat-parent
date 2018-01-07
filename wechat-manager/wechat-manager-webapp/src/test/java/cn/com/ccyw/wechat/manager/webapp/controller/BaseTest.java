package cn.com.ccyw.wechat.manager.webapp.controller;

import cn.com.ccyw.wechat.common.utils.StringUtil;
import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherData;
import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherDatatitle;
import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherHttpstatus;
import cn.com.ccyw.wechat.manager.mapper.weather.YxCcywWeatherDataMapper;
import cn.com.ccyw.wechat.manager.mapper.weather.YxCcywWeatherDatatitleMapper;
import cn.com.ccyw.wechat.manager.mapper.weather.YxCcywWeatherHttpstatusMapper;
import cn.com.ccyw.wechat.manager.service.core.MessageService;
import cn.com.ccyw.wechat.manager.service.weather.YxCcywWeatherDataService;
import cn.com.ccyw.wechat.manager.service.weather.YxCcywWeatherDatatitleService;
import cn.com.ccyw.wechat.manager.service.weather.YxCcywWeatherHttpstatusService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.webapp.controller
 * @description: TODO
 * @date 2017/12/31 14:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext*.xml"})
public class BaseTest {
    @Autowired
    private YxCcywWeatherHttpstatusService yxCcywWeatherHttpstatusService;
    @Autowired
    private YxCcywWeatherDatatitleService yxCcywWeatherDatatitleService;
    @Autowired
    private YxCcywWeatherDataService yxCcywWeatherDataService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private YxCcywWeatherHttpstatusMapper yxCcywWeatherHttpstatusMapper;
    @Autowired
    private YxCcywWeatherDatatitleMapper yxCcywWeatherDatatitleMapper;
    @Autowired
    private YxCcywWeatherDataMapper yxCcywWeatherDataMapper;

    @Test
    public void insert() throws Exception {
        YxCcywWeatherHttpstatus httpstatus = new YxCcywWeatherHttpstatus();
        httpstatus.setStatusid("201712310002");
        httpstatus.setDate("2017年12月31日");
        httpstatus.setStatus(200);
        httpstatus.setCity("成都");
        httpstatus.setCount(605);


        httpstatus.setMessage("今天天气晴朗");
        yxCcywWeatherHttpstatusService.insert(httpstatus);
    }

    @Test
    public void batchInsert() throws Exception {
        List<YxCcywWeatherData> yxCcywWeatherDatas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            YxCcywWeatherData yxCcywWeatherData = new YxCcywWeatherData();
            yxCcywWeatherDatas.add(yxCcywWeatherData);

            yxCcywWeatherData.setDataid("20171231361" + StringUtil.num2Str(i+1, 3));
            yxCcywWeatherData.setStatusid("20171231361");
            yxCcywWeatherData.setDate("29日星期五");
            yxCcywWeatherData.setSunrise("08:00");
            yxCcywWeatherData.setHigh("高温 12.0℃");
            yxCcywWeatherData.setLow("低温 5.0℃");
            yxCcywWeatherData.setSunset("18:11");
            yxCcywWeatherData.setAqi(200);
            yxCcywWeatherData.setFx("无持续风向");
            yxCcywWeatherData.setFl("<3级");
            yxCcywWeatherData.setType("多云");
            yxCcywWeatherData.setNotice("今日多云，骑上单车去看看世界吧");
        }
        System.out.println(yxCcywWeatherDataService.batchInsert(yxCcywWeatherDatas));
        System.out.println("完成");
    }

    @Test
    public void select() throws Exception {
        /*YxCcywWeatherHttpstatus yxCcywWeatherHttpstatus = new YxCcywWeatherHttpstatus();
        yxCcywWeatherHttpstatus.setDate("2018-01-01");
        yxCcywWeatherHttpstatus.setCity("成都");
        YxCcywWeatherHttpstatus httpstatus = yxCcywWeatherHttpstatusService.selectByEntitySelective(yxCcywWeatherHttpstatus);
        System.out.println(Objects.isNull(httpstatus) ? "object is null" : httpstatus.getMessage());*/

        /*YxCcywWeatherDatatitle datatitle = yxCcywWeatherDatatitleService.selectByPrimaryKey("20180101226");
        System.out.println(datatitle.getGanmao());
        YxCcywWeatherDatatitle datatitle1 = new YxCcywWeatherDatatitle();
        datatitle1.setDatatitleid("20180101226");
        datatitle1.setGanmao("儿童、老年人及心脏、呼吸系统疾病患者人群应减少长时间或高强度户外锻炼.............");
        yxCcywWeatherDatatitleService.updateByPrimaryKeySelective(datatitle1);
        YxCcywWeatherDatatitle datatitle2 = yxCcywWeatherDatatitleService.selectByPrimaryKey("20180101226");
        System.out.println(datatitle2.getGanmao());*/

        /*YxCcywWeatherDatatitle datatitle = yxCcywWeatherDatatitleService.selectByStatusId("20180101226");
        System.out.println(new Gson().toJson(datatitle));
        List<YxCcywWeatherData> datas = yxCcywWeatherDataService.selectByStatusId("20180101226");
        System.out.println(new Gson().toJson(datas));*/

        YxCcywWeatherHttpstatus record = new YxCcywWeatherHttpstatus();
        record.setCity("成都");
        record.setDate("2018-01-01");
        YxCcywWeatherHttpstatus httpstatus = yxCcywWeatherHttpstatusService.selectByEntitySelective(record);
        System.out.println(new Gson().toJson(httpstatus));
        YxCcywWeatherHttpstatus httpstatus2 = yxCcywWeatherHttpstatusService.selectByEntitySelective(record);
        System.out.println(new Gson().toJson(httpstatus2));

        /*System.out.println(messageService.getWeatherContent("遂宁"));*/
    }

    @Test
    public void daoSelect() throws Exception {
        YxCcywWeatherHttpstatus httpstatus = yxCcywWeatherHttpstatusMapper.selectByPrimaryKey("20180101226");
        System.out.println(new Gson().toJson(httpstatus));
        YxCcywWeatherHttpstatus httpstatus2 = yxCcywWeatherHttpstatusMapper.selectByPrimaryKey("20180101226");
        System.out.println(new Gson().toJson(httpstatus2));
    }
}
