package cn.com.ccyw.wechat.manager.mapper.weather;

import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherHttpstatus;

public interface YxCcywWeatherHttpstatusMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yx_ccyw_weather_httpstatus
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String statusid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yx_ccyw_weather_httpstatus
     *
     * @mbg.generated
     */
    int insert(YxCcywWeatherHttpstatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yx_ccyw_weather_httpstatus
     *
     * @mbg.generated
     */
    int insertSelective(YxCcywWeatherHttpstatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yx_ccyw_weather_httpstatus
     *
     * @mbg.generated
     */
    YxCcywWeatherHttpstatus selectByPrimaryKey(String statusid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yx_ccyw_weather_httpstatus
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(YxCcywWeatherHttpstatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yx_ccyw_weather_httpstatus
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(YxCcywWeatherHttpstatus record);
}