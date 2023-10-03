package com.rookie.controller.system;


import com.rookie.common.core.base.BaseController;
import com.rookie.common.core.dto.ResponseDTO;
import com.rookie.domain.role.command.AddRoleCommand;
import com.rookie.domain.role.command.UpdateRoleCommand;
import com.rookie.domain.role.db.ISysRoleService;
import com.rookie.domain.role.dto.SysRoleDTO;
import com.rookie.domain.role.query.RoleQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author yayee
 */
@Api(value = "Role Interfaces", tags = "Role Interfaces")
@RestController
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {

    @Resource
    private ISysRoleService roleService;

    @ApiOperation("Add role")
    @PostMapping("/add")
    public ResponseDTO<Void> add(@Valid @RequestBody AddRoleCommand command) {
        roleService.addRole(command);
        return ResponseDTO.ok();
    }

    @ApiOperation("Edit role")
    @PostMapping("/edit")
    public ResponseDTO<Void> edit(@Valid @RequestBody UpdateRoleCommand command) {
        roleService.updateRole(command);
        return ResponseDTO.ok();
    }

    @ApiOperation("Delete role")
    @GetMapping("/delete/{roleId}")
    public ResponseDTO<Void> deleteById(@PathVariable("roleId") Long roleId) {
        roleService.deleteById(roleId);
        return ResponseDTO.ok();
    }

    @ApiOperation("Query role By roleId")
    @GetMapping("/queryById/{roleId}")
    public ResponseDTO<SysRoleDTO> queryById(@PathVariable("roleId") Long roleId) {
        return ResponseDTO.ok(roleService.queryById(roleId));
    }

    @ApiOperation("Query role List")
    @PostMapping("list")
    public ResponseDTO<List<SysRoleDTO>> list(RoleQuery roleQuery) {
        return ResponseDTO.ok(roleService.findList(roleQuery));
    }

    @ApiOperation("Query role By roleName")
    @GetMapping("/queryByRoleName/{roleName}")
    public ResponseDTO<SysRoleDTO> queryByRoleName(@PathVariable("roleName") String roleName) {
        return ResponseDTO.ok(roleService.queryByRoleName(roleName));
    }
}

