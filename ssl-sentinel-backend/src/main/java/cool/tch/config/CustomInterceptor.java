package cool.tch.config;

import cool.tch.util.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cool.tch.common.Constant.*;

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

        // 登录的url可直接访问
        if (StringUtils.equals(REQUEST_URL_LOGIN, request.getRequestURI())) {
            return true;
        }

        // 验证token
        if(!TokenUtils.verify(request.getHeader(REQUEST_HEADER_ACCESS_TOKEN))){
            return false;
        }

        return true;
    }
}
