package cn.com.ccyw.wechat.menu.manager;

import cn.com.ccyw.wechat.menu.pojo.*;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.menu.manager
 * @description: 菜单管理器
 * @date 2017/12/25 20:18
 */
public class MenuManager {

    private static MenuManager instance = null;

    private MenuManager(){
    }

    public static MenuManager getInstance() {
        if (instance == null) {
            instance = new MenuManager();
        }
        return instance;
    }

    /**
     * 创建菜单
     * @return
     */
    public Menu getMenu() {
        KeyButton kbn11 = new KeyButton();
        kbn11.setName("天气情况");
        kbn11.setType("click");
        kbn11.setKey("kbn11");
        Menu menu = new Menu();
        menu.setButton(new Button[]{kbn11});
        return menu;
    }

}
