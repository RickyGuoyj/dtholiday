package com.eva.dtholiday.system.controller;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.RoleReq;
import com.eva.dtholiday.system.service.MenuService;
import com.eva.dtholiday.system.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/30 12:00
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@RestController
@RequestMapping("/erp/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private MenuService menuService;

    @GetMapping("/getRoleList")
    public ResponseApi getRoleList() {
        return roleService.getRoleList();
    }

    @GetMapping("/getAllMenuList")
    public ResponseApi getAllMenuList() {
        return menuService.getAllMenuList();
    }

    @PostMapping("/addRole")
    public ResponseApi addRole(@RequestBody RoleReq roleReq) {
        return roleService.addRole(roleReq);
    }

    @PostMapping("/updateRole")
    public ResponseApi updateRole(@RequestBody RoleReq roleReq) {
        return roleService.updateRole(roleReq);
    }

    @PostMapping("/getRoleInfo")
    public ResponseApi getRoleInfo(@RequestBody RoleReq roleReq) {
        return ResponseApi.ok(roleService.getRoleInfo(roleReq));
    }

    @PostMapping("/deleteRole")
    public ResponseApi deleteRole(@RequestBody RoleReq roleReq) {
        return roleService.deleteRole(roleReq);
    }
}
