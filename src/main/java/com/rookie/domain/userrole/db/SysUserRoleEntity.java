package com.rookie.domain.userrole.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rookie.common.core.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户和角色关联表
 * </p>
 *
 * @author yayee
 */
@Getter
@Setter
@TableName("sys_user_role")
@ApiModel(value = "SysUserRoleEntity对象", description = "用户和角色关联表")
public class SysUserRoleEntity extends BaseEntity<SysUserRoleEntity> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("角色ID")
    @TableField("role_id")
    private Long roleId;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
