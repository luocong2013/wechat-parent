package cn.com.ccyw.wechat.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.common.utils
 * @description: 字符串工具类
 * @date 2017/12/31 17:07
 */
public class StringUtil {

    /**
     * 格式化数字1为"001"形式
     *
     * @param num
     * @param ws
     * @return
     */
    public static String num2Str(int num, int ws) {
        return String.format("%0" + ws + "d", num);
    }

    /**
     * 字符串转换为数字
     *
     * @param str
     * @param ws
     * @return
     */
    public static int str2Num(String str, int ws) {
        String subStr = StringUtils.substring(str, str.length() - ws);
        return Integer.parseInt(subStr);
    }
}
