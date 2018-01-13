package cn.com.ccyw.wechat.manager.webapp.controller.weather;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.webapp.controller.weather
 * @description: TODO
 * @date 2018/1/13 16:34
 */
@Controller
@RequestMapping(value = "/weatherController")
public class WeatherController {

    @GetMapping(value = "/test/{name}")
    @ResponseBody
    public String test(@PathVariable("name") String name) {
        return "Hello, " + name;
    }

}
