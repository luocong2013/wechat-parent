package cn.com.ccyw.wechat.manager.service.timer;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.service.timer
 * @description: 定时任务：定时获取天气数据
 * @date 2017/12/31 15:14
 */
public interface WeatherTask {

    /**
     * 获取天气数据
     */
    void getWeatherData();
}
