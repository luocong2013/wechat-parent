package cn.com.ccyw.wechat.menu.pojo;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.menu.pojo
 * @description: 普通菜单按钮(子按钮) 带url属性
 * @date 2017/12/25 20:16
 */
public class UrlButton extends Button {
    private String type;

    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
