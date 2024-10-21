package cool.tch.common;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

import static cool.tch.common.ResponseCode.ERROR;
import static cool.tch.common.ResponseCode.SUCCESS;


/**
 * @author denchouka
 * @description Http响应结果
 * @date 2024/8/18 18:57
 */
@Builder
@ToString
@Getter
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -1724942765714360358L;

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * success
     * @return 响应结果
     * @param <T>
     */
    public static <T> ResponseResult<T> success() {
        return ResponseResult.<T>builder()
                .code(SUCCESS.getCode())
                .message(SUCCESS.getMessage())
                .build();
    }

    /**
     * success
     * @param data 响应数据
     * @return 响应结果
     * @param <T>
     */
    public static <T> ResponseResult<T> success(T data) {
        return ResponseResult.<T>builder()
                .code(SUCCESS.getCode())
                .message(SUCCESS.getMessage())
                .data(data)
                .build();
    }

    /**
     * success
     * @param message 响应消息
     * @param data 响应数据
     * @return 响应结果
     * @param <T>
     */
    public static <T> ResponseResult<T> success(String message, T data) {
        return ResponseResult.<T>builder()
                .code(SUCCESS.getCode())
                .message(message)
                .data(data)
                .build();
    }

    /**
     * error
     * @return 响应结果
     * @param <T>
     */
    public static <T> ResponseResult<T> error() {
        return ResponseResult.<T>builder()
                .code(ERROR.getCode())
                .message(ERROR.getMessage())
                .build();
    }

    /**
     * error
     * @param message 响应消息
     * @return 响应结果
     * @param <T>
     */
    public static <T> ResponseResult<T> error(String message) {
        return ResponseResult.<T>builder()
                .code(ERROR.getCode())
                .message(message)
                .build();
    }

    /**
     * error
     * @param code 响应码
     * @param message 响应消息
     * @return 响应结果
     * @param <T>
     */
    public static <T> ResponseResult<T> error(Integer code, String message) {
        return ResponseResult.<T>builder()
                .code(code)
                .message(message)
                .build();
    }

    /**
     * 共通的响应结果
     * @param code 响应码
     * @param message 响应消息
     * @param data 响应数据
     * @return 响应结果
     * @param <T>
     */
    public static <T> ResponseResult<T> result(Integer code, String message, T data) {
        return ResponseResult.<T>builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }
}
