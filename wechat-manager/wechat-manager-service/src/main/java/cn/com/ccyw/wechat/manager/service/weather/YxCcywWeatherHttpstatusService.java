package cn.com.ccyw.wechat.manager.service.weather;

import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherHttpstatus;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.service.weather
 * @description: TODO
 * @date 2017/12/21 22:29
 */
public interface YxCcywWeatherHttpstatusService {
    /**
     * 根据主键删除数据
     * @param statusid
     * @return
     */
    int deleteByPrimaryKey(String statusid);

    /**
     * 插入数据
     * @param record
     * @return
     */
    int insert(YxCcywWeatherHttpstatus record);

    /**
     * 插入非空数据
     * @param record
     * @return
     */
    int insertSelective(YxCcywWeatherHttpstatus record);

    /**
     * 根据主键查询数据
     * @param statusid
     * @return
     */
    YxCcywWeatherHttpstatus selectByPrimaryKey(String statusid);

    /**
     * 根据主键修改非空数据
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(YxCcywWeatherHttpstatus record);

    /**
     * 根据主键修改数据
     * @param record
     * @return
     */
    int updateByPrimaryKey(YxCcywWeatherHttpstatus record);

    /**
     * 条件查询（空字段不查询）
     * @param record
     * @return
     */
    YxCcywWeatherHttpstatus selectByEntitySelective(YxCcywWeatherHttpstatus record);
}
