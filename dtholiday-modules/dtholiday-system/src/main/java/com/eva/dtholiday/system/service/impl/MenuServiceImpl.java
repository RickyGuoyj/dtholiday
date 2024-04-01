package com.eva.dtholiday.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.dto.MenuAsyncTree;
import com.eva.dtholiday.commons.dao.entity.Menu;
import com.eva.dtholiday.commons.dao.mapper.MenuMapper;
import com.eva.dtholiday.commons.dao.mapper.RoleMenuMapper;
import com.eva.dtholiday.commons.enums.BusinessErrorCodeEnum;
import com.eva.dtholiday.commons.exception.BusinessException;
import com.eva.dtholiday.system.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/30 12:57
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public ResponseApi getAllMenuList() {
        List<Menu> menuList = menuMapper.selectList(new QueryWrapper<Menu>().eq(Menu.TYPE, "C"));
        if (CollectionUtils.isEmpty(menuList)) {
            throw new BusinessException(BusinessErrorCodeEnum.LOAD_MENU_ERROR.getMessageCN(), BusinessErrorCodeEnum.LOAD_MENU_ERROR.getCode());
        }
        //构建菜单树
        MenuAsyncTree menuAsyncTree = new MenuAsyncTree();
        convertMenuListToMenuTree(menuList, "root", menuAsyncTree);
        return ResponseApi.ok(menuAsyncTree);
    }

    @Override
    public MenuAsyncTree getMenuListByRoleCode(String roleCode) {
        List<Menu> menuList = menuMapper.selectMenuByRoleCode(roleCode);
        menuList = menuList.stream().filter(menu -> menu.getType().equals("C")).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(menuList)) {
            throw new BusinessException(BusinessErrorCodeEnum.LOAD_MENU_ERROR.getMessageCN(), BusinessErrorCodeEnum.LOAD_MENU_ERROR.getCode());
        }
        //构建菜单树
        MenuAsyncTree menuAsyncTree = new MenuAsyncTree();
        convertMenuListToMenuTree(menuList, "root", menuAsyncTree);
        return menuAsyncTree;
    }

    private void convertMenuListToMenuTree(List<Menu> menuList, String node, MenuAsyncTree menuAsyncTree) {
        //说明是根节点
        if (!StringUtils.hasText(menuAsyncTree.getCode())) {
           try {
               Menu rootMenu = menuList.stream().filter(menu -> node.equals(menu.getMenuCode())).collect(Collectors.toList()).get(0);
               menuAsyncTree.setCode(rootMenu.getMenuCode());
               menuAsyncTree.setName(rootMenu.getMenuName());
               menuAsyncTree.setUrl(rootMenu.getUrl());
           }catch (Exception e){
               log.error("无法找到根节点，{}",JSON.toJSON(menuList));
               return;
           }
        }

        //todo 每次遍历list会比遍历hashmap慢很多，可以把入参调整为map<code,menu>
        List<Menu> matchMenuList = menuList.stream().filter(menu -> node.equals(menu.getParentCode())).collect(Collectors.toList());
        List<MenuAsyncTree> children = matchMenuList.stream().map(menu -> {
            MenuAsyncTree subNode = new MenuAsyncTree();
            subNode.setName(menu.getMenuName());
            subNode.setCode(menu.getMenuCode());
            subNode.setUrl(menu.getUrl());
            //添加子节点的方式是递归地调用convertMenuListToMenuTree方法，并将子节点添加到当前节点的children列表中，深度遍历的逻辑
            convertMenuListToMenuTree(menuList, menu.getMenuCode(), subNode);
            return subNode;
        }).collect(Collectors.toList());
        menuAsyncTree.setChildren(children);
    }

}
