package cn.com.ccyw.wechat.manager.service.weather;

import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherDatatitle;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.service.weather
 * @description: TODO
 * @date 2017/12/31 14:11
 */
public interface YxCcywWeatherDatatitleService {
    /**
     * 根据主键删除数据
     *
     * @param datatitleid
     * @return
     */
    int deleteByPrimaryKey(String datatitleid);

    /**
     * 插入数据
     *
     * @param record
     * @return
     */
    int insert(YxCcywWeatherDatatitle record);

    /**
     * 插入非空数据
     *
     * @param record
     * @return
     */
    int insertSelective(YxCcywWeatherDatatitle record);

    /**
     * 根据主键查询数据
     *
     * @param datatitleid
     * @return
     */
    YxCcywWeatherDatatitle selectByPrimaryKey(String datatitleid);

    /**
     * 根据主键修改非空数据
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(YxCcywWeatherDatatitle record);

    /**
     * 根据主键修改数据
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(YxCcywWeatherDatatitle record);

    /**
     * 通过statusid查询数据
     * @param statusid
     * @return
     */
    YxCcywWeatherDatatitle selectByStatusId(String statusid);
}
