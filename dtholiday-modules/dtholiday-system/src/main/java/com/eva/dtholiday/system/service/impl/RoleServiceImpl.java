package com.eva.dtholiday.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.Role;
import com.eva.dtholiday.commons.dao.entity.RoleMenu;
import com.eva.dtholiday.commons.dao.entity.UserRole;
import com.eva.dtholiday.commons.dao.mapper.RoleMapper;
import com.eva.dtholiday.commons.dao.mapper.RoleMenuMapper;
import com.eva.dtholiday.commons.dao.mapper.UserRoleMapper;
import com.eva.dtholiday.commons.dao.req.RoleReq;
import com.eva.dtholiday.commons.dao.resp.RoleResp;
import com.eva.dtholiday.commons.enums.BusinessErrorCodeEnum;
import com.eva.dtholiday.commons.exception.BusinessException;
import com.eva.dtholiday.commons.utils.MyStringUtils;
import com.eva.dtholiday.system.service.MenuService;
import com.eva.dtholiday.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/30 12:02
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private MenuService menuService;

    @Override
    public ResponseApi getRoleList() {
        List<RoleResp> roleRespList = new ArrayList<>();
        List<Role> roleList = roleMapper.selectList(null);
        if (!CollectionUtils.isEmpty(roleList)) {
            roleList.forEach(role -> {
                RoleResp roleResp = new RoleResp();
                roleResp.setCode(role.getRoleCode());
                roleResp.setName(role.getRoleName());
                roleRespList.add(roleResp);
            });
        }
        return ResponseApi.ok(roleRespList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi addRole(RoleReq roleReq) {
        //参数校验
        if (!StringUtils.hasText(roleReq.getName()) || CollectionUtils.isEmpty(roleReq.getMenuCodes())) {
            throw new BusinessException(BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getMessageCN(), BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getCode());
        }
        //角色同名校验
        Role oldRole = roleMapper.selectOne(new QueryWrapper<Role>().eq(Role.ROLE_NAME, roleReq.getName()));
        if (Objects.nonNull(oldRole)) {
            throw new BusinessException(BusinessErrorCodeEnum.ROLE_NAME_EXIST.getMessageCN(), BusinessErrorCodeEnum.ROLE_NAME_EXIST.getCode());
        }
        //增加一个角色，及角色与菜单的绑定关系
        Role role = new Role();
        role.setRoleCode(MyStringUtils.getUUID());
        role.setRoleName(roleReq.getName());

        List<RoleMenu> roleMenuList = roleReq.getMenuCodes().stream().map(menuCode -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleCode(role.getRoleCode());
            roleMenu.setMenuCode(menuCode);
            return roleMenu;
        }).collect(Collectors.toList());

        roleMapper.insert(role);
        roleMenuMapper.bulkInsert(roleMenuList);

        RoleResp roleResp = new RoleResp();
        roleResp.setCode(role.getRoleCode());
        roleResp.setName(role.getRoleName());
        return ResponseApi.ok(roleResp);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi updateRole(RoleReq roleReq) {
        //参数校验
        if (!StringUtils.hasText(roleReq.getName()) || CollectionUtils.isEmpty(roleReq.getMenuCodes())) {
            throw new BusinessException(BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getMessageCN(), BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getCode());
        }
        //角色存在性校验
        Role oldRole = roleMapper.selectOne(new QueryWrapper<Role>().eq(Role.ROLE_CODE, roleReq.getCode()));
        if (Objects.isNull(oldRole)) {
            throw new BusinessException(BusinessErrorCodeEnum.ROLE_NOT_EXIST.getMessageCN(), BusinessErrorCodeEnum.ROLE_NOT_EXIST.getCode());
        }
        if (!oldRole.getRoleName().equals(roleReq.getName())) {
            //角色同名校验
            Role nameRole = roleMapper.selectOne(new QueryWrapper<Role>().eq(Role.ROLE_NAME, roleReq.getName()));
            if (Objects.nonNull(nameRole) && !nameRole.getRoleCode().equals(roleReq.getCode())) {
                throw new BusinessException(BusinessErrorCodeEnum.ROLE_NAME_EXIST.getMessageCN(), BusinessErrorCodeEnum.ROLE_NAME_EXIST.getCode());
            }
            oldRole.setRoleName(roleReq.getName());
        }
        //更新角色
        oldRole.setUpdateTime(new Date());
        roleMapper.updateById(oldRole);

        //删除原角色与菜单的绑定关系
        roleMenuMapper.delete(new QueryWrapper<RoleMenu>().eq(RoleMenu.ROLE_CODE, roleReq.getCode()));

        List<RoleMenu> roleMenuList = roleReq.getMenuCodes().stream().map(menuCode -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleCode(oldRole.getRoleCode());
            roleMenu.setMenuCode(menuCode);
            return roleMenu;
        }).collect(Collectors.toList());
        roleMenuMapper.bulkInsert(roleMenuList);

        RoleResp roleResp = new RoleResp();
        roleResp.setCode(oldRole.getRoleCode());
        roleResp.setName(oldRole.getRoleName());
        return ResponseApi.ok(roleResp);
    }

    @Override
    public RoleResp getRoleInfo(RoleReq roleReq) {
        if (!StringUtils.hasText(roleReq.getCode())) {
            throw new BusinessException(BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getMessageCN(), BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getCode());
        }
        Role role = roleMapper.selectOne(new QueryWrapper<Role>().eq(Role.ROLE_CODE, roleReq.getCode()));
        if (Objects.isNull(role)) {
            throw new BusinessException(BusinessErrorCodeEnum.ROLE_NOT_EXIST.getMessageCN(), BusinessErrorCodeEnum.ROLE_NOT_EXIST.getCode());
        }
        RoleResp roleResp = new RoleResp();
        roleResp.setCode(role.getRoleCode());
        roleResp.setName(role.getRoleName());
        roleResp.setMenuAsyncTree(menuService.getMenuListByRoleCode(role.getRoleCode()));
        return roleResp;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi deleteRole(RoleReq roleReq) {
        if (!StringUtils.hasText(roleReq.getCode())) {
            throw new BusinessException(BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getMessageCN(), BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getCode());
        }
        //如果有用户与该角色绑定，则提示无法删除
        List<UserRole> userRoleList = userRoleMapper.selectList(new QueryWrapper<UserRole>().eq(UserRole.ROLE_CODE, roleReq.getCode()));
        if (!CollectionUtils.isEmpty(userRoleList)) {
            throw new BusinessException(BusinessErrorCodeEnum.ROLE_BINDING_WITH_USER.getMessageCN(), BusinessErrorCodeEnum.ROLE_BINDING_WITH_USER.getCode());
        }
        //删除角色
        roleMapper.delete(new QueryWrapper<Role>().eq(Role.ROLE_CODE, roleReq.getCode()));
        //删除角色与菜单的绑定关系
        roleMenuMapper.delete(new QueryWrapper<RoleMenu>().eq(RoleMenu.ROLE_CODE, roleReq.getCode()));
        RoleResp roleResp = new RoleResp();
        BeanUtils.copyProperties(roleReq, roleResp);
        return ResponseApi.ok(roleResp);
    }

}
