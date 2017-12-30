package cn.com.ccyw.wechat.menu.pojo;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.menu.pojo
 * @description: 普通菜单按钮(子按钮) 带key属性
 * @date 2017/12/25 20:15
 */
public class KeyButton extends Button {
    private String type;

    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
