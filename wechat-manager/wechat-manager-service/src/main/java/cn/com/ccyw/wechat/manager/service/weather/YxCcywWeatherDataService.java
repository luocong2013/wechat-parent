package cn.com.ccyw.wechat.manager.service.weather;

import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherData;

import java.util.List;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.service.weather
 * @description: TODO
 * @date 2017/12/21 22:28
 */
public interface YxCcywWeatherDataService {
    /**
     * 根据主键删除数据
     * @param dataid
     * @return
     */
    int deleteByPrimaryKey(String dataid);

    /**
     * 插入数据
     * @param record
     * @return
     */
    int insert(YxCcywWeatherData record);

    /**
     * 插入非空数据
     * @param record
     * @return
     */
    int insertSelective(YxCcywWeatherData record);

    /**
     * 根据主键查询数据
     * @param dataid
     * @return
     */
    YxCcywWeatherData selectByPrimaryKey(String dataid);

    /**
     * 根据主键修改非空数据
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(YxCcywWeatherData record);

    /**
     * 根据主键修改数据
     * @param record
     * @return
     */
    int updateByPrimaryKey(YxCcywWeatherData record);

    /**
     * 批量插入数据
     * @param records
     * @return
     */
    int batchInsert(List<YxCcywWeatherData> records);
}
