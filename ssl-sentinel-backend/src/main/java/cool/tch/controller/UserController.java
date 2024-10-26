package cool.tch.controller;

import cool.tch.common.ResponseResult;
import cool.tch.dto.LoginDto;
import cool.tch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseResult login(@Validated @RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }


}
