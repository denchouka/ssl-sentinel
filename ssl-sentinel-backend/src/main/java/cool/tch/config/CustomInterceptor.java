package cool.tch.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import cool.tch.common.ResponseResult;
import cool.tch.exception.BusinessException;
import cool.tch.util.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static cool.tch.common.Constant.*;
import static cool.tch.common.ResponseCode.HTTP_UNAUTHORIZED;

/**
 * @author denchouka
 * @description 自定义拦截器
 * @date 2024/10/26 20:41
 */
public class CustomInterceptor implements HandlerInterceptor {

    /**
     * 在请求处理之前做一些事情
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // access-control-request-headers是跨域时候的预检请求，跳过
        boolean equals = StringUtils.equalsIgnoreCase(REQUEST_METHOD_PRE_FLIGHT, request.getMethod());
        boolean blank = StringUtils.isBlank(request.getHeader(REQUEST_HEADER_PRE_FLIGHT));
        if(!blank && equals){
            return true;
        }

        // 登录或退出 的url可直接访问
        if (StringUtils.equals(REQUEST_URL_LOGIN, request.getRequestURI()) || StringUtils.equals(REQUEST_URL_LOGOUT, request.getRequestURI())) {
            return true;
        }

        // 验证token
        if(!TokenUtils.verify(request.getHeader(REQUEST_HEADER_ACCESS_TOKEN))){
            // 封装Http响应结果
            ResponseResult<Object> error = ResponseResult.error(HTTP_UNAUTHORIZED.getCode(), HTTP_UNAUTHORIZED.getMessage());
            // 转换成json
            String errorStr = new ObjectMapper().writeValueAsString(error);
            // 将json字符串写入Http响应中
            returnJson(response, errorStr);
            return false;
        }

        return true;
    }

    /**
     * 将json字符串写入Http响应中
     * @param response Http响应
     * @param json json字符串
     */
    private void returnJson(HttpServletResponse response, String json){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            throw new BusinessException("响应结果写入失败");
        } finally {
            if (writer != null){
                writer.close();
            }
        }
    }
}
