package cool.tch.controller;

import cool.tch.common.ResponseResult;
import cool.tch.dto.LoginDto;
import cool.tch.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cool.tch.common.Constant.*;

/**
 * @author denchouka
 * @description TODO
 * @date 2024/10/20 22:31
 */
@RestController()
@RequestMapping(REQUEST_URL_PREFIX)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(REQUEST_URL_LOGIN)
    public ResponseResult login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        // 登录时请求头中没有"User-Name"，为了能在拦截器中获取到username，添加到响应头中
        response.addHeader(ACCESS_CONTROL_EXPOSE_HEADERS,REQUEST_HEADER_USER_NAME);
        response.setHeader(REQUEST_HEADER_USER_NAME, loginDto.getUsername());
        return userService.login(loginDto);
    }

    @GetMapping(REQUEST_URL_LOGOUT)
    public ResponseResult logout(HttpServletRequest request) {
        // 获取token
        String token = request.getHeader(REQUEST_HEADER_ACCESS_TOKEN);
        return userService.logout(token);
    }


}
