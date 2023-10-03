package com.rookie.domain.user.db;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rookie.common.exception.ApiException;
import com.rookie.common.exception.error.ErrorCode.Business;
import com.rookie.domain.user.command.AddUserCommand;
import com.rookie.domain.user.command.UpdateUserCommand;
import com.rookie.domain.user.command.UpdateUserPasswordCommand;
import com.rookie.domain.user.dto.SysUserDTO;
import com.rookie.domain.user.query.UserQuery;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author yayee
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements ISysUserService {

    @Override
    public void addUser(AddUserCommand command) {
        SysUserEntity entity = BeanUtil.copyProperties(command, SysUserEntity.class);
        if (this.isUserNameDuplicated(entity.getUsername(), null)) {
            throw new ApiException(Business.USER_NAME_IS_NOT_UNIQUE);
        }
        if (this.isPhoneDuplicated(entity.getPhone(), null)) {
            throw new ApiException(Business.USER_PHONE_IS_NOT_UNIQUE);
        }
        baseMapper.insert(entity);
    }

    @Override
    public void updateUser(UpdateUserCommand command) {
        SysUserEntity entity = BeanUtil.copyProperties(command, SysUserEntity.class);
        if (ObjectUtil.isEmpty(entity)) {
            throw new ApiException(Business.COMMON_OBJECT_NOT_FOUND, command.getUserId(), "用户");
        }
        if (this.isUserNameDuplicated(entity.getUsername(), entity.getUserId())) {
            throw new ApiException(Business.USER_NAME_IS_NOT_UNIQUE);
        }
        if (this.isPhoneDuplicated(entity.getPhone(), entity.getUserId())) {
            throw new ApiException(Business.USER_PHONE_IS_NOT_UNIQUE);
        }
        baseMapper.updateById(entity);
    }

    @Override
    public void deleteById(Long userId) {
        baseMapper.deleteById(userId);
    }

    @Override
    public SysUserDTO queryById(Long userId) {
        SysUserEntity entity = baseMapper.selectById(userId);
        return ObjectUtil.isNotEmpty(entity) ? BeanUtil.copyProperties(entity, SysUserDTO.class) : null;
    }

    @Override
    public List<SysUserDTO> findList(UserQuery query) {
        return baseMapper.findList(query);
    }

    @Override
    public void updatePassword(UpdateUserPasswordCommand command) {
        SysUserEntity entity = baseMapper.selectById(command.getUserId());
        if (ObjectUtil.isEmpty(entity)) {
            throw new ApiException(Business.COMMON_OBJECT_NOT_FOUND, command.getUserId(), "用户");
        }
        if (StringUtils.equals(command.getNewPassword(), command.getOldPassword())) {
            throw new ApiException(Business.USER_NEW_PASSWORD_IS_THE_SAME_AS_OLD);
        }
        if (StringUtils.equals(entity.getPassword(), command.getOldPassword())) {
            entity.setPassword(command.getNewPassword());
            baseMapper.updateById(entity);
        } else {
            throw new ApiException(Business.USER_PASSWORD_IS_NOT_CORRECT);
        }
    }

    @Override
    public SysUserDTO queryByUsername(String username) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        SysUserEntity entity = baseMapper.selectOne(queryWrapper);
        return ObjectUtil.isNotEmpty(entity) ? BeanUtil.copyProperties(entity, SysUserDTO.class) : null;
    }

    @Override
    public boolean isUserNameDuplicated(String username, Long userId) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne(userId != null, "user_id", userId)
            .eq("username", username);
        return baseMapper.exists(queryWrapper);
    }

    @Override
    public boolean isPhoneDuplicated(String phone, Long userId) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne(userId != null, "user_id", userId)
            .eq("phone", phone);
        return baseMapper.exists(queryWrapper);
    }
}
