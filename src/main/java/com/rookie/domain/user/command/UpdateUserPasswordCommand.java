package com.rookie.domain.user.command;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author yayee
 */
@Data
public class UpdateUserPasswordCommand {

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("新密码")
    @NotEmpty(message = "could not be empty")
    @Length(min = 1, max = 32, message = "password should be 1-32")
    private String newPassword;

    @ApiModelProperty("旧密码")
    @NotEmpty(message = "could not be empty")
    @Length(min = 1, max = 32, message = "password should be 1-32")
    private String oldPassword;
}
