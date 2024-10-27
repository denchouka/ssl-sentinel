package cool.tch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static cool.tch.common.Constant.*;

/**
 * @author denchouka
 * @description 自定义配置
 * @date 2024/10/26 18:46
 */
@Configuration
public class CustomConfig implements WebMvcConfigurer {

    /**
     * 跨域配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 配置全局 CORS
        registry.addMapping("/**") // 指定所有路径
                .allowedOrigins(REQUEST_ORIGINS_LOCALHOST, REQUEST_ORIGINS_DOMAIN) // 允许所有源
                .allowedMethods(REQUEST_METHOD_GET, REQUEST_METHOD_POST) // 允许的方法
                .allowedHeaders(REQUEST_HEADER_CONTENT_TYPE, REQUEST_HEADER_ACCESS_TOKEN, REQUEST_HEADER_AUTHORIZATION, REQUEST_HEADER_USER_NAME) // 允许所有头
                .allowCredentials(true) // 是否允许发送凭证
                .maxAge(3600); // 预检请求的有效期（秒）
    }

    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor())
                .addPathPatterns("/**");
    }
}
