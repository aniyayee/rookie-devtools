package com.rookie.domain.user.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yayee
 */
@Data
public class UserQuery {

    @ApiModelProperty("用户账号")
    private String username;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    private String phone;
}
