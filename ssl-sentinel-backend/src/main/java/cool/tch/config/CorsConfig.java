package cool.tch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author denchouka
 * @description 全局跨域
 * @date 2024/10/26 18:46
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 配置全局 CORS
        registry.addMapping("/**") // 指定所有路径
                .allowedOrigins("http://localhost:5173", "https://ssl.tch.cool") // 允许所有源
                .allowedMethods("GET", "POST") // 允许的方法
                .allowedHeaders("Content-Type", "Access-Token", "Authorization", "User-Name") // 允许所有头
                .allowCredentials(true) // 是否允许发送凭证
                .maxAge(3600); // 预检请求的有效期（秒）
    }
}
