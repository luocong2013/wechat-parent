package cn.com.ccyw.wechat.manager.service.timer.impl;

import cn.com.ccyw.wechat.manager.dao.timer.WeatherTaskDao;
import cn.com.ccyw.wechat.manager.entity.core.json.Httpstatus;
import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherData;
import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherDatatitle;
import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherHttpstatus;
import cn.com.ccyw.wechat.manager.mapper.weather.YxCcywWeatherDataMapper;
import cn.com.ccyw.wechat.manager.mapper.weather.YxCcywWeatherDatatitleMapper;
import cn.com.ccyw.wechat.manager.mapper.weather.YxCcywWeatherHttpstatusMapper;
import cn.com.ccyw.wechat.manager.service.timer.WeatherTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.service.timer.impl
 * @description: 定时任务：定时获取天气数据
 * @date 2017/12/31 15:26
 */
@Component
public class WeatherTaskImpl implements WeatherTask {

    @Autowired
    private WeatherTaskDao weatherTaskDao;
    @Autowired
    private YxCcywWeatherHttpstatusMapper yxCcywWeatherHttpstatusMapper;
    @Autowired
    private YxCcywWeatherDatatitleMapper yxCcywWeatherDatatitleMapper;
    @Autowired
    private YxCcywWeatherDataMapper yxCcywWeatherDataMapper;

    /**
     * 每天6点 定时获取天气数据
     */
    @Scheduled(cron = "0 0 6 * * ?")
    @Override
    public void getWeatherData() {
        Httpstatus httpstatus = weatherTaskDao.getWeatherData("成都");
        YxCcywWeatherHttpstatus yxCcywWeatherHttpstatus = weatherTaskDao.getHttpstatus(httpstatus);
        if (Objects.nonNull(yxCcywWeatherHttpstatus)) {
            YxCcywWeatherDatatitle yxCcywWeatherDatatitle = weatherTaskDao.getDatatitle(httpstatus);
            List<YxCcywWeatherData> yxCcywWeatherDatas = weatherTaskDao.getData(httpstatus);
            yxCcywWeatherHttpstatusMapper.insertSelective(yxCcywWeatherHttpstatus);
            yxCcywWeatherDatatitleMapper.insertSelective(yxCcywWeatherDatatitle);
            yxCcywWeatherDataMapper.batchInsert(yxCcywWeatherDatas);
        }
    }
}
