package com.rookie.domain.role.command;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author yayee
 */
@Data
public class AddRoleCommand {

    @ApiModelProperty("角色名称")
    @NotEmpty(message = "could not be empty")
    @Length(min = 1, max = 32, message = "roleName should be 1-32")
    private String roleName;

    @ApiModelProperty("角色权限字符串")
    @NotEmpty(message = "could not be empty")
    @Length(min = 1, max = 16, message = "roleKey should be 1-16")
    private String roleKey;

    @ApiModelProperty("备注")
    private String remark;
}
