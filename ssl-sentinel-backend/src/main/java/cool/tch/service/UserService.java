package cool.tch.service;

import cool.tch.common.ResponseResult;
import cool.tch.dto.LoginDto;

/**
 * @author denchouka
 * @description TODO
 * @date 2024/10/20 22:24
 */
public interface UserService {

    /**
     * 用户登录
     * @param loginDto
     * @return
     */
    ResponseResult login(LoginDto loginDto);

    /**
     * 用户退出
     * @param token 请求的token
     * @return
     */
    ResponseResult logout(String token);
}
