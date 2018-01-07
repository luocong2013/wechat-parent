package cn.com.ccyw.wechat.manager.service.weather.impl;

import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherHttpstatus;
import cn.com.ccyw.wechat.manager.mapper.weather.YxCcywWeatherHttpstatusMapper;
import cn.com.ccyw.wechat.manager.service.weather.YxCcywWeatherHttpstatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    /**
     * 删除数据
     * 并清除缓存
     * @param statusid
     * @return
     */
    @CacheEvict(value = "weatherCache", key = "#root.target + #statusid")
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

    /**
     * 通过statusid查询数据
     * 并将返回值进行缓存
     * @param statusid
     * @return
     */
    @Cacheable(value = "weatherCache", key = "#root.target + #statusid")
    @Override
    public YxCcywWeatherHttpstatus selectByPrimaryKey(String statusid) {
        return yxCcywWeatherHttpstatusMapper.selectByPrimaryKey(statusid);
    }

    /**
     * 修改数据
     * 并清除缓存
     * @param record
     * @return
     */
    @CacheEvict(value = "weatherCache", key = "#root.target + #record.statusid")
    @Override
    public int updateByPrimaryKeySelective(YxCcywWeatherHttpstatus record) {
        return yxCcywWeatherHttpstatusMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 修改数据
     * 并清除缓存
     * @param record
     * @return
     */
    @CacheEvict(value = "weatherCache", key = "#root.target + #record.statusid")
    @Override
    public int updateByPrimaryKey(YxCcywWeatherHttpstatus record) {
        return yxCcywWeatherHttpstatusMapper.updateByPrimaryKey(record);
    }

    /**
     * 通过对象数据查询数据
     * 并将返回值进行缓存
     * @param record
     * @return
     */
    @CachePut(value = "weatherCache", key = "#root.target + #record.date + #record.city")
    @Override
    public YxCcywWeatherHttpstatus selectByEntitySelective(YxCcywWeatherHttpstatus record) {
        return yxCcywWeatherHttpstatusMapper.selectByEntitySelective(record);
    }
}
