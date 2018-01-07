package cn.com.ccyw.wechat.manager.dao.timer;

import cn.com.ccyw.wechat.common.cons.Constants;
import cn.com.ccyw.wechat.common.utils.DateUtil;
import cn.com.ccyw.wechat.common.utils.HttpClientUtil;
import cn.com.ccyw.wechat.common.utils.JsonUtil;
import cn.com.ccyw.wechat.common.utils.StringUtil;
import cn.com.ccyw.wechat.manager.entity.core.json.Data;
import cn.com.ccyw.wechat.manager.entity.core.json.Datatitle;
import cn.com.ccyw.wechat.manager.entity.core.json.Httpstatus;
import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherData;
import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherDatatitle;
import cn.com.ccyw.wechat.manager.entity.weather.YxCcywWeatherHttpstatus;
import com.xiaoleilu.hutool.date.DatePattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.dao.timer
 * @description: 定时获取天气数据Dao层
 * @date 2017/12/31 16:01
 */
@Component
public class WeatherTaskDao {

    /**
     * 获取天气数据
     *
     * @param city
     * @return
     */
    public Httpstatus getWeatherData(String city) {
        Httpstatus httpstatus = null;
        String json = HttpClientUtil.sendGet(city);
        if (StringUtils.isNotBlank(json)) {
            httpstatus = JsonUtil.json2Bean(json, Httpstatus.class);
        }
        return httpstatus;
    }

    /**
     * 获取http请求天气数据状态
     * @param httpstatus
     * @return
     */
    public YxCcywWeatherHttpstatus getHttpstatus(Httpstatus httpstatus) {
        Validate.notNull(httpstatus);
        YxCcywWeatherHttpstatus yxCcywWeatherHttpstatus = null;
        if (httpstatus.getStatus() == HttpStatus.SC_OK) {
            yxCcywWeatherHttpstatus = new YxCcywWeatherHttpstatus();
            String statusid = httpstatus.getDate() + StringUtil.num2Str(httpstatus.getCount(), Constants.HTTP_STATUS_ID_WS);
            yxCcywWeatherHttpstatus.setStatusid(statusid);
            yxCcywWeatherHttpstatus.setDate(DateUtil.formatDate(DateUtil.parseDate(httpstatus.getDate(), DatePattern.PURE_DATE_PATTERN)));
            yxCcywWeatherHttpstatus.setMessage(httpstatus.getMessage());
            yxCcywWeatherHttpstatus.setStatus(httpstatus.getStatus());
            yxCcywWeatherHttpstatus.setCity(httpstatus.getCity());
            yxCcywWeatherHttpstatus.setCount(httpstatus.getCount());
        }
        return yxCcywWeatherHttpstatus;
    }

    /**
     * 获取天气数据标题
     * @param httpstatus
     * @return
     */
    public YxCcywWeatherDatatitle getDatatitle(Httpstatus httpstatus) {
        Validate.notNull(httpstatus);
        YxCcywWeatherDatatitle yxCcywWeatherDatatitle = null;
        if (httpstatus.getStatus() == HttpStatus.SC_OK) {
            Datatitle datatitle = httpstatus.getData();
            yxCcywWeatherDatatitle = new YxCcywWeatherDatatitle();
            String statusid = httpstatus.getDate() + StringUtil.num2Str(httpstatus.getCount(), Constants.HTTP_STATUS_ID_WS);
            yxCcywWeatherDatatitle.setDatatitleid(statusid);
            yxCcywWeatherDatatitle.setStatusid(statusid);
            yxCcywWeatherDatatitle.setShidu(datatitle.getShidu());
            yxCcywWeatherDatatitle.setPm25(datatitle.getPm25());
            yxCcywWeatherDatatitle.setPm10(datatitle.getPm10());
            yxCcywWeatherDatatitle.setQuality(datatitle.getQuality());
            yxCcywWeatherDatatitle.setWendu(datatitle.getWendu());
            yxCcywWeatherDatatitle.setGanmao(datatitle.getGanmao());
        }
        return yxCcywWeatherDatatitle;
    }

    /**
     * 天气数据
     * @param httpstatus
     * @return
     */
    public List<YxCcywWeatherData> getData(Httpstatus httpstatus) {
        Validate.notNull(httpstatus);
        List<YxCcywWeatherData> yxCcywWeatherDatas = new ArrayList<>();
        if (httpstatus.getStatus() == HttpStatus.SC_OK) {
            Datatitle datatitle = httpstatus.getData();
            List<Data> dataLst = datatitle.getForecast();
            dataLst.add(0, datatitle.getYesterday());
            String statusid = httpstatus.getDate() + StringUtil.num2Str(httpstatus.getCount(), Constants.HTTP_STATUS_ID_WS);
            for (int i = 0; i < dataLst.size(); i++) {
                Data data = dataLst.get(i);
                YxCcywWeatherData yxCcywWeatherData = new YxCcywWeatherData();
                yxCcywWeatherDatas.add(yxCcywWeatherData);

                yxCcywWeatherData.setDataid(statusid + StringUtil.num2Str(i + 1, Constants.DADA_ID_WS));
                yxCcywWeatherData.setStatusid(statusid);
                yxCcywWeatherData.setDate(data.getDate());
                yxCcywWeatherData.setSunrise(data.getSunrise());
                yxCcywWeatherData.setHigh(data.getHigh());
                yxCcywWeatherData.setLow(data.getLow());
                yxCcywWeatherData.setSunset(data.getSunset());
                yxCcywWeatherData.setAqi(data.getAqi());
                yxCcywWeatherData.setFx(data.getFx());
                yxCcywWeatherData.setFl(data.getFl());
                yxCcywWeatherData.setType(data.getType());
                yxCcywWeatherData.setNotice(data.getNotice());
            }
        }
        return yxCcywWeatherDatas;
    }
}
