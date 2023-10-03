package com.rookie.domain.role.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rookie.common.core.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author yayee
 */
@Getter
@Setter
@TableName("sys_role")
@ApiModel(value = "SysRoleEntity对象", description = "角色信息表")
public class SysRoleEntity extends BaseEntity<SysRoleEntity> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色ID")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    @ApiModelProperty("角色名称")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty("角色权限字符串")
    @TableField("role_key")
    private String roleKey;


    @Override
    public Serializable pkVal() {
        return this.roleId;
    }

}
