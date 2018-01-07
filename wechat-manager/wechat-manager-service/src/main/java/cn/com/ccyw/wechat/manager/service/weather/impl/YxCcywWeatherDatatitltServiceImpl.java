package cn.com.ccyw.wechat.manager.service.weather.impl;

import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherDatatitle;
import cn.com.ccyw.wechat.manager.mapper.weather.YxCcywWeatherDatatitleMapper;
import cn.com.ccyw.wechat.manager.service.weather.YxCcywWeatherDatatitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    /**
     * 删除数据
     * 并清除缓存
     * @param datatitleid
     * @return
     */
    @CacheEvict(value = "weatherCache", key = "#root.target + #datatitleid")
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

    /**
     * 通过datatitleid查询数据
     * 并将返回值进行缓存
     * @param datatitleid
     * @return
     */
    @Cacheable(value = "weatherCache", key = "#root.target + #datatitleid")
    @Override
    public YxCcywWeatherDatatitle selectByPrimaryKey(String datatitleid) {
        return yxCcywWeatherDatatitleMapper.selectByPrimaryKey(datatitleid);
    }

    /**
     * 修改数据
     * 并清除缓存
     * @param record
     * @return
     */
    @CacheEvict(value = "weatherCache", key = "#root.target + #record.datatitleid")
    @Override
    public int updateByPrimaryKeySelective(YxCcywWeatherDatatitle record) {
        return yxCcywWeatherDatatitleMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 修改数据
     * 并清除缓存
     * @param record
     * @return
     */
    @CacheEvict(value = "weatherCache", key = "#root.target + #record.datatitleid")
    @Override
    public int updateByPrimaryKey(YxCcywWeatherDatatitle record) {
        return yxCcywWeatherDatatitleMapper.updateByPrimaryKey(record);
    }

    /**
     * 通过statusid查询数据
     * 并将返回值进行缓存
     * @param statusid
     * @return
     */
    @Cacheable(value = "weatherCache", key = "#root.target + #statusid")
    @Override
    public YxCcywWeatherDatatitle selectByStatusId(String statusid) {
        return yxCcywWeatherDatatitleMapper.selectByStatusId(statusid);
    }
}
