package com.rookie.domain.user.db;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rookie.domain.user.command.AddUserCommand;
import com.rookie.domain.user.command.UpdateUserCommand;
import com.rookie.domain.user.command.UpdateUserPasswordCommand;
import com.rookie.domain.user.dto.SysUserDTO;
import com.rookie.domain.user.query.UserQuery;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author yayee
 */
public interface ISysUserService extends IService<SysUserEntity> {

    void addUser(AddUserCommand command);

    void updateUser(UpdateUserCommand command);

    void deleteById(Long userId);

    SysUserDTO queryById(Long userId);

    List<SysUserDTO> findList(UserQuery query);

    void updatePassword(UpdateUserPasswordCommand command);

    SysUserDTO queryByUsername(String username);

    boolean isUserNameDuplicated(String username, Long userId);

    boolean isPhoneDuplicated(String phone, Long userId);
}
