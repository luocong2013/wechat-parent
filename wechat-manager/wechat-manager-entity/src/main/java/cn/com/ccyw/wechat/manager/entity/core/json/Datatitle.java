package cn.com.ccyw.wechat.manager.entity.core.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.entity.core.json
 * @description: TODO
 * @date 2017/12/30 17:21
 */
public class Datatitle implements Serializable {
    /**
     * 湿度
     */
    private String shidu;
    /**
     * pm2.5
     */
    private Integer pm25;
    /**
     * pm10
     */
    private Integer pm10;
    /**
     * 空气质量
     */
    private String quality;
    /**
     * 温度
     */
    private String wendu;
    /**
     * 感冒
     */
    private String ganmao;
    private Data yesterday;
    private List<Data> forecast;

    public String getShidu() {
        return shidu;
    }

    public void setShidu(String shidu) {
        this.shidu = shidu;
    }

    public Integer getPm25() {
        return pm25;
    }

    public void setPm25(Integer pm25) {
        this.pm25 = pm25;
    }

    public Integer getPm10() {
        return pm10;
    }

    public void setPm10(Integer pm10) {
        this.pm10 = pm10;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public Data getYesterday() {
        return yesterday;
    }

    public void setYesterday(Data yesterday) {
        this.yesterday = yesterday;
    }

    public List<Data> getForecast() {
        return forecast;
    }

    public void setForecast(List<Data> forecast) {
        this.forecast = forecast;
    }
}
