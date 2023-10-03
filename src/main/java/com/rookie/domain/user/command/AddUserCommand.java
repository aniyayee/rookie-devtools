package com.rookie.domain.user.command;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author yayee
 */
@Data
public class AddUserCommand {

    @ApiModelProperty("用户账号")
    @NotEmpty(message = "could not be empty")
    @Length(min = 1, max = 32, message = "username should be 1-32")
    private String username;

    @ApiModelProperty("密码")
    @NotEmpty(message = "could not be empty")
    @Length(min = 1, max = 32, message = "password should be 1-32")
    private String password;

    @ApiModelProperty("用户昵称")
    @NotEmpty(message = "could not be empty")
    @Length(min = 1, max = 32, message = "nickname should be 1-32")
    private String nickname;

    @ApiModelProperty("用户邮箱")
    @NotEmpty(message = "could not be empty")
    @Pattern(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", message = "invalid email")
    private String email;

    @ApiModelProperty("手机号码")
    @NotEmpty(message = "could not be empty")
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "invalid phone")
    private String phone;

    @ApiModelProperty("备注")
    private String remark;
}
