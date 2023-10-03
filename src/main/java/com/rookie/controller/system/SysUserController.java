package com.rookie.controller.system;


import com.rookie.common.core.base.BaseController;
import com.rookie.common.core.dto.ResponseDTO;
import com.rookie.domain.user.command.AddUserCommand;
import com.rookie.domain.user.command.UpdateUserCommand;
import com.rookie.domain.user.command.UpdateUserPasswordCommand;
import com.rookie.domain.user.db.ISysUserService;
import com.rookie.domain.user.dto.SysUserDTO;
import com.rookie.domain.user.query.UserQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author yayee
 */
@Slf4j
@Api(value = "User Interfaces", tags = "User Interfaces")
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {

    @Resource
    private ISysUserService userService;

    @ApiOperation("Add user")
    @PostMapping("/add")
    public ResponseDTO<Void> add(@Valid @RequestBody AddUserCommand command) {
        userService.addUser(command);
        return ResponseDTO.ok();
    }

    @ApiOperation("Edit user")
    @PostMapping("/edit")
    public ResponseDTO<Void> edit(@Valid @RequestBody UpdateUserCommand command) {
        userService.updateUser(command);
        return ResponseDTO.ok();
    }

    @ApiOperation("Delete user")
    @GetMapping("/delete/{userId}")
    public ResponseDTO<Void> deleteById(@PathVariable("userId") Long userId) {
        userService.deleteById(userId);
        return ResponseDTO.ok();
    }

    @ApiOperation("Query user By userId")
    @GetMapping("/queryById/{userId}")
    public ResponseDTO<SysUserDTO> queryById(@PathVariable("userId") Long userId) {
        log.info("userId: {}", userId);
        log.info("userId: {}", userId);
        System.out.println("userId: " + userId);
        System.out.println("userId: " + userId);
        return ResponseDTO.ok(userService.queryById(userId));
    }

    @ApiOperation("Query user List")
    @PostMapping("/list")
    public ResponseDTO<List<SysUserDTO>> list(UserQuery userQuery) {
        return ResponseDTO.ok(userService.findList(userQuery));
    }

    @ApiOperation("Update user password")
    @PostMapping("/password")
    public ResponseDTO<Void> updatePassword(@Valid @RequestBody UpdateUserPasswordCommand command) {
        userService.updatePassword(command);
        return ResponseDTO.ok();
    }

    @ApiOperation("Query user By username")
    @GetMapping("/queryByUsername/{username}")
    public ResponseDTO<SysUserDTO> queryByUsername(@PathVariable("username") String username) {
        return ResponseDTO.ok(userService.queryByUsername(username));
    }
}

