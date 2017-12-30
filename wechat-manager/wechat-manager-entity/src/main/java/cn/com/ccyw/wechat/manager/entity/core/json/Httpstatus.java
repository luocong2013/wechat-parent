package cn.com.ccyw.wechat.manager.entity.core.json;

import java.io.Serializable;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.entity.core.json
 * @description: TODO
 * @date 2017/12/30 17:19
 */
public class Httpstatus implements Serializable {
    /**
     * 日期
     */
    private String date;
    /**
     * 消息
     */
    private String message;
    /**
     * http请求状态
     */
    private Integer status;
    /**
     * 城市
     */
    private String city;
    /**
     * 次数
     */
    private Integer count;
    private Datatitle data;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Datatitle getData() {
        return data;
    }

    public void setData(Datatitle data) {
        this.data = data;
    }
}
