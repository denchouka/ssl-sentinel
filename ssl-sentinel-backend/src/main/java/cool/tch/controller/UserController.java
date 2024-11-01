package cool.tch.controller;

import cool.tch.common.ResponseResult;
import cool.tch.dto.LoginDto;
import cool.tch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static cool.tch.common.Constant.ACCESS_CONTROL_EXPOSE_HEADERS;
import static cool.tch.common.Constant.REQUEST_HEADER_USER_NAME;

/**
 * @author denchouka
 * @description TODO
 * @date 2024/10/20 22:31
 */
@RestController()
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseResult login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        // 登录时请求头中没有"User-Name"，为了能在拦截器中获取到username，添加到响应头中
        response.addHeader(ACCESS_CONTROL_EXPOSE_HEADERS,REQUEST_HEADER_USER_NAME);
        response.setHeader(REQUEST_HEADER_USER_NAME, loginDto.getUsername());
        return userService.login(loginDto);
    }


}
