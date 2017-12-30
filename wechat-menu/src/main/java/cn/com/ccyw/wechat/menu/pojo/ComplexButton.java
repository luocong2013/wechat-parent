package cn.com.ccyw.wechat.menu.pojo;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.menu.pojo
 * @description: 复杂菜单按钮(父按钮)
 * @date 2017/12/25 20:15
 */
public class ComplexButton extends Button {
    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
