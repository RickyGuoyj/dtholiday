package com.eva.dtholiday.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eva.dtholiday.commons.dao.entity.Menu;
import com.eva.dtholiday.commons.dao.entity.Role;
import com.eva.dtholiday.commons.dao.entity.User;
import com.eva.dtholiday.commons.dao.entity.UserRole;
import com.eva.dtholiday.commons.dao.mapper.MenuMapper;
import com.eva.dtholiday.commons.dao.mapper.RoleMapper;
import com.eva.dtholiday.commons.dao.mapper.UserMapper;
import com.eva.dtholiday.commons.dao.mapper.UserRoleMapper;
import com.eva.dtholiday.commons.enums.BusinessErrorCodeEnum;
import com.eva.dtholiday.commons.exception.BusinessException;
import com.eva.dtholiday.security.entity.DtHolidayUser;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/20 9:31
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Configuration
@AllArgsConstructor
public class DtHolidayUserDetailService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名获取用户
        User user = userMapper.selectOne(new QueryWrapper<User>().eq(User.USER_NAME, username));
        if (user != null) {
            // 根据用户获取角色（目前只允许一个用户对应一个角色）
            List<Role> roleList = roleMapper.selectRoleByUserCode(user.getUserCode());
            // 根据角色获取权限(type F 是接口，C是菜单)
            List<String> permsList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(roleList)) {
                List<Menu> menuList = menuMapper.selectMenuByRoleCode(roleList.get(0).getRoleCode());
                permsList = menuList.stream().filter(menu -> menu.getType().equals("F")).map(Menu::getPerms).collect(Collectors.toList());
            }
            String[] permissions = permsList.toArray(new String[0]);
//            String[] permissions = new String[]{"add_user"};
            List<GrantedAuthority> userAuths = AuthorityUtils.createAuthorityList(permissions);
            DtHolidayUser dtUser = new DtHolidayUser(user.getId(), user.getUserName(), user.getPassword(), userAuths);
            return dtUser;
        } else {
            throw new UsernameNotFoundException(BusinessErrorCodeEnum.LOGIN_ACCOUNT_NO_EXIST.getMessageCN());
        }
    }
}