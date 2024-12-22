package cool.tch.config;

import cool.tch.util.NetUtils;
import cool.tch.util.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

import static cool.tch.common.Constant.*;

/**
 * @author denchouka
 * @description 对全局响应进行增强
 * @date 2024/11/1 20:38
 */
@ControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice {

    /**
     * 指定哪些类型的响应需要处理
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // true表示处理所有类型的响应
        return true;
    }

    /**
     * 在写入响应体之前执行
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        HttpHeaders responseHeaders = response.getHeaders();
        // 获取当前请求的url
        String path = request.getURI().getPath();
        // 用户退出的时候不返回新的token
        if (StringUtils.equals(REQUEST_URL_LOGOUT, path)) {
            return body;
        }

        // 获取userName
        String userName = null;
        if (StringUtils.equals(REQUEST_URL_LOGIN, path)) {
            // 登录的时候，请求头中还没有"User-Name"，从响应头中获取
            userName = response.getHeaders().get(REQUEST_HEADER_USER_NAME).get(0);
        } else {
            userName = request.getHeaders().get(REQUEST_HEADER_USER_NAME).get(0);
        }

        // 返回新的token
        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        responseHeaders.set(ACCESS_CONTROL_EXPOSE_HEADERS, REQUEST_HEADER_ACCESS_TOKEN);
        responseHeaders.set(REQUEST_HEADER_ACCESS_TOKEN, TokenUtils.token(userName, NetUtils.getIPAddress(servletRequest)));

        return body;
    }
}
