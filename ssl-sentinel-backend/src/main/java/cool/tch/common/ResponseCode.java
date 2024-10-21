package cool.tch.common;

/**
 * @author denchouka
 * @description Http响应状态码
 * @date 2024/8/18 18:52
 */
public enum ResponseCode {

    SUCCESS(200, "SUCCESS"),

    ERROR(400, "ERROR");

    public Integer code;
    public String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
