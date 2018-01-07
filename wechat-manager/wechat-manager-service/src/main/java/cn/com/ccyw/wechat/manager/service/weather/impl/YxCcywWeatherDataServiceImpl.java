package cn.com.ccyw.wechat.manager.service.weather.impl;

import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherData;
import cn.com.ccyw.wechat.manager.mapper.weather.YxCcywWeatherDataMapper;
import cn.com.ccyw.wechat.manager.service.weather.YxCcywWeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    /**
     * 删除数据
     * 并清除缓存
     * @param dataid
     * @return
     */
    @CacheEvict(value = "weatherCache", key = "#root.target + #dataid")
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

    /**
     * 通过dataid查询数据
     * 并将返回值进行缓存
     * @param dataid
     * @return
     */
    @Cacheable(value = "weatherCache", key = "#root.target + #dataid")
    @Override
    public YxCcywWeatherData selectByPrimaryKey(String dataid) {
        return yxCcywWeatherDataMapper.selectByPrimaryKey(dataid);
    }

    /**
     * 修改数据
     * 并清除缓存
     * @param record
     * @return
     */
    @CacheEvict(value = "weatherCache", key = "#root.target + #record.dataid")
    @Override
    public int updateByPrimaryKeySelective(YxCcywWeatherData record) {
        return yxCcywWeatherDataMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 修改数据
     * 并清除缓存
     * @param record
     * @return
     */
    @CacheEvict(value = "weatherCache", key = "#root.target + #record.dataid")
    @Override
    public int updateByPrimaryKey(YxCcywWeatherData record) {
        return yxCcywWeatherDataMapper.updateByPrimaryKey(record);
    }

    @Override
    public int batchInsert(List<YxCcywWeatherData> records) {
        return yxCcywWeatherDataMapper.batchInsert(records);
    }

    /**
     * 通过statusid查询数据
     * 并将返回值进行缓存
     * @param statusid
     * @return
     */
    @Cacheable(value = "weatherCache", key = "#root.target + #statusid")
    @Override
    public List<YxCcywWeatherData> selectByStatusId(String statusid) {
        return yxCcywWeatherDataMapper.selectByStatusId(statusid);
    }
}
