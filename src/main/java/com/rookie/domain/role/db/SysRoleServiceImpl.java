package com.rookie.domain.role.db;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rookie.common.exception.ApiException;
import com.rookie.common.exception.error.ErrorCode.Business;
import com.rookie.domain.role.command.AddRoleCommand;
import com.rookie.domain.role.command.UpdateRoleCommand;
import com.rookie.domain.role.dto.SysRoleDTO;
import com.rookie.domain.role.query.RoleQuery;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author yayee
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements ISysRoleService {

    @Override
    public void addRole(AddRoleCommand command) {
        SysRoleEntity entity = BeanUtil.copyProperties(command, SysRoleEntity.class);
        if (this.isRoleNameDuplicated(entity.getRoleName(), null)) {
            throw new ApiException(Business.ROLE_NAME_IS_NOT_UNIQUE);
        }
        baseMapper.insert(entity);
    }

    @Override
    public void updateRole(UpdateRoleCommand command) {
        SysRoleEntity entity = BeanUtil.copyProperties(command, SysRoleEntity.class);
        if (ObjectUtil.isEmpty(entity)) {
            throw new ApiException(Business.COMMON_OBJECT_NOT_FOUND, command.getRoleId(), "角色");
        }
        if (this.isRoleNameDuplicated(entity.getRoleName(), command.getRoleId())) {
            throw new ApiException(Business.ROLE_NAME_IS_NOT_UNIQUE);
        }
        baseMapper.updateById(entity);
    }

    @Override
    public void deleteById(Long roleId) {
        baseMapper.deleteById(roleId);
    }

    @Override
    public SysRoleDTO queryById(Long roleId) {
        SysRoleEntity entity = baseMapper.selectById(roleId);
        return ObjectUtil.isNotEmpty(entity) ? BeanUtil.copyProperties(entity, SysRoleDTO.class) : null;
    }

    @Override
    public List<SysRoleDTO> findList(RoleQuery roleQuery) {
        List<SysRoleEntity> entityList = lambdaQuery().like(StringUtils.isNotEmpty(roleQuery.getRoleName()),
                SysRoleEntity::getRoleName, roleQuery.getRoleName())
            .like(StringUtils.isNotEmpty(roleQuery.getRoleKey()), SysRoleEntity::getRoleKey, roleQuery.getRoleKey())
            .list();
        return BeanUtil.copyToList(entityList, SysRoleDTO.class);
    }

    @Override
    public SysRoleDTO queryByRoleName(String roleName) {
        QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name", roleName);
        SysRoleEntity entity = baseMapper.selectOne(queryWrapper);
        return ObjectUtil.isNotEmpty(entity) ? BeanUtil.copyProperties(entity, SysRoleDTO.class) : null;
    }

    @Override
    public boolean isRoleNameDuplicated(String roleName, Long roleId) {
        QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne(roleId != null, "role_id", roleId)
            .eq("role_name", roleName);
        return baseMapper.exists(queryWrapper);
    }
}
