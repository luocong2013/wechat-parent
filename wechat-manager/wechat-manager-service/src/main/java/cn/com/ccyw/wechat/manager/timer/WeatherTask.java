package cn.com.ccyw.wechat.manager.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.timer
 * @description: 定时任务：定时获取天气数据
 * @date 2017/12/31 15:14
 */
@Component
public class WeatherTask {

    @Scheduled(cron = "0/5 * * * * ?")
    public void taskJob() {
        System.out.println("task job");
    }
}
