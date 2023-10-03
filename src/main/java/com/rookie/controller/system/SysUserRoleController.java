package com.rookie.controller.system;


import com.rookie.common.core.base.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户和角色关联表 前端控制器
 * </p>
 *
 * @author yayee
 */
@Api(value = "UserRole Interfaces", tags = "UserRole Interfaces")
@RestController
@RequestMapping("/system/user-role")
public class SysUserRoleController extends BaseController {

}

