package cn.com.ccyw.wechat.manager.webapp.controller;

import cn.com.ccyw.wechat.common.utils.DateUtil;

import java.util.Date;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.webapp.controller
 * @description: TODO
 * @date 2017/12/24 18:04
 */
public class TestHttpMethod {
    public static void main(String[] args) {
        /*String json = "{\"date\":\"20171230\",\"message\":\"Success !\",\"status\":200,\"city\":\"成都\",\"count\":602,\"data\":{\"shidu\":\"70%\",\"pm25\":123.0,\"pm10\":361.0,\"quality\":\"重度污染\",\"wendu\":\"7\",\"ganmao\":\"老年人及心脏、呼吸系统疾病患者人群应停留在室内，停止户外运动，一般人群减少户外运动\",\"yesterday\":{\"date\":\"29日星期五\",\"sunrise\":\"08:00\",\"high\":\"高温 12.0℃\",\"low\":\"低温 5.0℃\",\"sunset\":\"18:11\",\"aqi\":200.0,\"fx\":\"无持续风向\",\"fl\":\"<3级\",\"type\":\"多云\",\"notice\":\"今日多云，骑上单车去看看世界吧\"},\"forecast\":[{\"date\":\"30日星期六\",\"sunrise\":\"08:00\",\"high\":\"高温 12.0℃\",\"low\":\"低温 3.0℃\",\"sunset\":\"18:12\",\"aqi\":149.0,\"fx\":\"无持续风向\",\"fl\":\"<3级\",\"type\":\"多云\",\"notice\":\"绵绵的云朵，形状千变万化\"},{\"date\":\"31日星期日\",\"sunrise\":\"08:01\",\"high\":\"高温 15.0℃\",\"low\":\"低温 3.0℃\",\"sunset\":\"18:12\",\"aqi\":99.0,\"fx\":\"无持续风向\",\"fl\":\"<3级\",\"type\":\"晴\",\"notice\":\"天气干燥，请适当增加室内湿度\"},{\"date\":\"01日星期一\",\"sunrise\":\"08:01\",\"high\":\"高温 12.0℃\",\"low\":\"低温 3.0℃\",\"sunset\":\"18:13\",\"aqi\":132.0,\"fx\":\"无持续风向\",\"fl\":\"<3级\",\"type\":\"多云\",\"notice\":\"今日多云，骑上单车去看看世界吧\"},{\"date\":\"02日星期二\",\"sunrise\":\"08:01\",\"high\":\"高温 9.0℃\",\"low\":\"低温 4.0℃\",\"sunset\":\"18:14\",\"aqi\":158.0,\"fx\":\"无持续风向\",\"fl\":\"<3级\",\"type\":\"阴\",\"notice\":\"阴天没有晴天的明朗，却依然明亮\"},{\"date\":\"03日星期三\",\"sunrise\":\"08:01\",\"high\":\"高温 8.0℃\",\"low\":\"低温 2.0℃\",\"sunset\":\"18:14\",\"aqi\":170.0,\"fx\":\"无持续风向\",\"fl\":\"<3级\",\"type\":\"小雨\",\"notice\":\"雾蒙蒙的雨天，最喜欢一个人听音乐\"}]}}\n";
        //String json = "{\"message\":\"Check the parameters.\"}";
        Httpstatus httpstatus = JsonUtil.json2Bean(json, Httpstatus.class);
        Datatitle datatitle = httpstatus.getData();
        System.out.println(httpstatus.getCity());

        List<Data> dataLst = datatitle.getForecast();
        dataLst.add(0, datatitle.getYesterday());*/

        System.out.println(DateUtil.formatDate(new Date(), "dd日"));



        /*String str = "成都天气";
        if (str.endsWith(Constants.REQ_MESSAGE_SUFFIX)) {
            String city = StringUtils.substring(str, 0, StringUtils.lastIndexOf(str, Constants.REQ_MESSAGE_SUFFIX));
            String json = HttpClientUtil.sendGet(city);
            Httpstatus httpstatus = JsonUtil.json2Bean(json, Httpstatus.class);
            System.out.println(httpstatus.getCity()+"-"+httpstatus.getMessage());
        }*/
    }
}
