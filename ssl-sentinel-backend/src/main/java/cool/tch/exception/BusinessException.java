package cool.tch.exception;

import lombok.Data;

import static cool.tch.common.ResponseCode.ERROR;

/**
 * @author blue
 * @description: 异常返回类
 * @date 2021/7/19 10:40
 */
@Data
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 6401507641198338287L;

    /** 异常代码 */
    protected Integer code;

    /** 异常消息 */
    protected String message;

    public BusinessException(String msg) {
        super(msg);
        this.code = ERROR.getCode();
        this.message = msg;
    }

    @Override
    public String toString() {
        return "errorCode: " + code + ", message: " + message;
    }
}
