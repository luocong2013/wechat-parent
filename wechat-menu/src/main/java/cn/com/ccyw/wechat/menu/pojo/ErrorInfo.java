package cn.com.ccyw.wechat.menu.pojo;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.menu.pojo
 * @description: 错误信息
 * @date 2017/12/25 22:02
 */
public class ErrorInfo {
    /**
     * 错误码
     */
    private int errcode;
    /**
     * 错误信息
     */
    private String errmsg;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
