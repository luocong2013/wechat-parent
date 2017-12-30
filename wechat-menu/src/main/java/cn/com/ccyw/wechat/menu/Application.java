package cn.com.ccyw.wechat.menu;

import cn.com.ccyw.wechat.menu.manager.HttpsManager;
import cn.com.ccyw.wechat.menu.manager.MenuManager;

import java.io.UnsupportedEncodingException;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.menu
 * @description: 创建菜单入口
 * @date 2017/12/25 20:17
 */
public class Application {

    public static void main(String[] args) throws UnsupportedEncodingException {
        HttpsManager httpsManager = new HttpsManager();
        int resultCode = httpsManager.createMenu(MenuManager.getInstance().getMenu());
        if (resultCode == 0) {
            System.out.println("菜单创建成功！");
        } else {
            System.out.println("菜单创建失败，错误码：" + resultCode);
        }
    }
}
