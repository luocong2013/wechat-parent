package cn.com.ccyw.wechat.manager.service.weather.impl;

import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherData;
import cn.com.ccyw.wechat.manager.mapper.weather.YxCcywWeatherDataMapper;
import cn.com.ccyw.wechat.manager.service.weather.YxCcywWeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.service.weather.impl
 * @description: Service层
 * @date 2017/12/24 16:29
 */
@Service
public class YxCcywWeatherDataServiceImpl implements YxCcywWeatherDataService {

    @Autowired
    private YxCcywWeatherDataMapper yxCcywWeatherDataMapper;

    @Override
    public int deleteByPrimaryKey(String dataid) {
        return yxCcywWeatherDataMapper.deleteByPrimaryKey(dataid);
    }

    @Override
    public int insert(YxCcywWeatherData record) {
        return yxCcywWeatherDataMapper.insert(record);
    }

    @Override
    public int insertSelective(YxCcywWeatherData record) {
        return yxCcywWeatherDataMapper.insertSelective(record);
    }

    @Override
    public YxCcywWeatherData selectByPrimaryKey(String dataid) {
        return yxCcywWeatherDataMapper.selectByPrimaryKey(dataid);
    }

    @Override
    public int updateByPrimaryKeySelective(YxCcywWeatherData record) {
        return yxCcywWeatherDataMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(YxCcywWeatherData record) {
        return yxCcywWeatherDataMapper.updateByPrimaryKey(record);
    }

    @Override
    public int batchInsert(List<YxCcywWeatherData> records) {
        return yxCcywWeatherDataMapper.batchInsert(records);
    }
}
