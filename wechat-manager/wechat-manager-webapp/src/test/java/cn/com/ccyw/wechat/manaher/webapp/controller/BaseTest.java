package cn.com.ccyw.wechat.manaher.webapp.controller;

import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherHttpstatus;
import cn.com.ccyw.wechat.manager.service.weather.YxCcywWeatherHttpstatusService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manaher.webapp.controller
 * @description: TODO
 * @date 2017/12/31 14:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext*.xml"})
public class BaseTest {
    @Autowired
    private YxCcywWeatherHttpstatusService yxCcywWeatherHttpstatusService;

    @Test
    public void insert() throws Exception {
        YxCcywWeatherHttpstatus httpstatus = new YxCcywWeatherHttpstatus();
        httpstatus.setStatusid("201712310001");
        httpstatus.setDate("2017年12月31日");
        httpstatus.setStatus(200);
        httpstatus.setCity("成都");
        httpstatus.setCount(605);
        httpstatus.setMessage("今天天气晴朗");
        yxCcywWeatherHttpstatusService.insert(httpstatus);
    }
}
