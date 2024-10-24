package cool.tch.service.impl;

import cool.tch.common.ResponseResult;
import cool.tch.dto.LoginDto;
import cool.tch.entity.User;
import cool.tch.mapper.UserMapper;
import cool.tch.service.UserService;
import cool.tch.util.EncryptUtils;
import cool.tch.util.GoogleAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author denchouka
 * @description TODO
 * @date 2024/10/20 22:30
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseResult login(LoginDto loginDto) {
        // 用户是否存在
        User user = userMapper.selectUser(loginDto.getUsername());
        Assert.notNull(user, "用户不存在!");

        // 校验密码
        boolean validate = EncryptUtils.validate(loginDto.getPassword(), user.getPassword());
        Assert.isTrue(validate, "密码错误!");

        // 验证MFA动态码
        boolean checkCode = GoogleAuthenticator.checkCode(user.getSecretKey(), Long.parseLong(loginDto.getCode()), System.currentTimeMillis());
        Assert.isTrue(checkCode, "MFA动态码已失效或错误!");

        // 登录成功
        return ResponseResult.success();
    }
}
