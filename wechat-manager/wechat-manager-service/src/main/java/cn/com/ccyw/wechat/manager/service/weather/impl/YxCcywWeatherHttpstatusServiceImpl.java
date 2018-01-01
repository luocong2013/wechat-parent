package cn.com.ccyw.wechat.manager.service.weather.impl;

import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherHttpstatus;
import cn.com.ccyw.wechat.manager.mapper.weather.YxCcywWeatherHttpstatusMapper;
import cn.com.ccyw.wechat.manager.service.weather.YxCcywWeatherHttpstatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.service.weather.impl
 * @description: Service层
 * @date 2017/12/24 16:34
 */
@Service
public class YxCcywWeatherHttpstatusServiceImpl implements YxCcywWeatherHttpstatusService {

    @Autowired
    private YxCcywWeatherHttpstatusMapper yxCcywWeatherHttpstatusMapper;

    @Override
    public int deleteByPrimaryKey(String statusid) {
        return yxCcywWeatherHttpstatusMapper.deleteByPrimaryKey(statusid);
    }

    @Override
    public int insert(YxCcywWeatherHttpstatus record) {
        return yxCcywWeatherHttpstatusMapper.insert(record);
    }

    @Override
    public int insertSelective(YxCcywWeatherHttpstatus record) {
        return yxCcywWeatherHttpstatusMapper.insertSelective(record);
    }

    @Override
    public YxCcywWeatherHttpstatus selectByPrimaryKey(String statusid) {
        return yxCcywWeatherHttpstatusMapper.selectByPrimaryKey(statusid);
    }

    @Override
    public int updateByPrimaryKeySelective(YxCcywWeatherHttpstatus record) {
        return yxCcywWeatherHttpstatusMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(YxCcywWeatherHttpstatus record) {
        return yxCcywWeatherHttpstatusMapper.updateByPrimaryKey(record);
    }

    @Override
    public YxCcywWeatherHttpstatus selectByEntitySelective(YxCcywWeatherHttpstatus record) {
        return yxCcywWeatherHttpstatusMapper.selectByEntitySelective(record);
    }
}
