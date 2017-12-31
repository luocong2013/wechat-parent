package cn.com.ccyw.wechat.manager.webapp.controller;

import cn.com.ccyw.wechat.common.utils.StringUtil;
import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherData;
import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherHttpstatus;
import cn.com.ccyw.wechat.manager.service.weather.YxCcywWeatherDataService;
import cn.com.ccyw.wechat.manager.service.weather.YxCcywWeatherDatatitleService;
import cn.com.ccyw.wechat.manager.service.weather.YxCcywWeatherHttpstatusService;
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

}
