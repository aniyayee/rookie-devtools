package com.rookie.domain.role.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yayee
 */
@Data
public class SysRoleDTO {

    @ApiModelProperty("角色ID")
    private Long roleId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色权限字符串")
    private String roleKey;

    @ApiModelProperty("备注")
    private String remark;
}
