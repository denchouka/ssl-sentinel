package cool.tch.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author denchouka
 * @description 用户登录用dto
 * @date 2024/10/21 22:27
 */
@Data
public class LoginDto implements Serializable {

    private static final long serialVersionUID = 6375862469866387784L;

    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "用户名不符合格式")
    @Length(min = 8, max = 20, message = "用户名由字母组成并且至少6位")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 8, max = 20, message = "密码不能小于6位数")
    private String password;

    @NotBlank(message = "MFA动态码不能为空")
    @Length(min = 6, max = 6, message = "MFA动态码必须是6位数字")
    @Pattern(regexp = "^[0-9]+$", message = "MFA动态码不符合格式")
    private String code;
}
