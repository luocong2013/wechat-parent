package cn.com.ccyw.wechat.manager.service.weather.impl;

import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherDatatitle;
import cn.com.ccyw.wechat.manager.mapper.weather.YxCcywWeatherDatatitleMapper;
import cn.com.ccyw.wechat.manager.service.weather.YxCcywWeatherDatatitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.service.weather.impl
 * @description: Service层
 * @date 2017/12/31 14:13
 */
@Service
public class YxCcywWeatherDatatitltServiceImpl implements YxCcywWeatherDatatitleService {

    @Autowired
    private YxCcywWeatherDatatitleMapper yxCcywWeatherDatatitleMapper;

    @Override
    public int deleteByPrimaryKey(String datatitleid) {
        return yxCcywWeatherDatatitleMapper.deleteByPrimaryKey(datatitleid);
    }

    @Override
    public int insert(YxCcywWeatherDatatitle record) {
        return yxCcywWeatherDatatitleMapper.insert(record);
    }

    @Override
    public int insertSelective(YxCcywWeatherDatatitle record) {
        return yxCcywWeatherDatatitleMapper.insertSelective(record);
    }

    @Override
    public YxCcywWeatherDatatitle selectByPrimaryKey(String datatitleid) {
        return yxCcywWeatherDatatitleMapper.selectByPrimaryKey(datatitleid);
    }

    @Override
    public int updateByPrimaryKeySelective(YxCcywWeatherDatatitle record) {
        return yxCcywWeatherDatatitleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(YxCcywWeatherDatatitle record) {
        return yxCcywWeatherDatatitleMapper.updateByPrimaryKey(record);
    }
}
