package cn.my.kdmall.common.exception;

public enum BizCode {

    UNKNOW_EXCEPTION(1000, "未知异常"),
    VAILD_EXCEPTION(1001, "参数格式校验失败");

    private int code;
    private String msg;

    BizCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
