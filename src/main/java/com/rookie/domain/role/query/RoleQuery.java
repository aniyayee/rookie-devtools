package com.rookie.domain.role.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yayee
 */
@Data
public class RoleQuery {

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色权限字符串")
    private String roleKey;
}
