package com.eva.dtholiday.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eva.dtholiday.commons.dao.entity.User;
import com.eva.dtholiday.commons.dao.mapper.UserMapper;
import com.eva.dtholiday.commons.enums.BusinessErrorCodeEnum;
import com.eva.dtholiday.security.entity.DtHolidayUser;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.selectOne(new QueryWrapper<User>().eq(User.USER_NAME, username));
        if (user != null) {
            // 根据用户获取角色，及权限
            String[] permissions = new String[]{"add_user"};
            List<GrantedAuthority> userAuths = AuthorityUtils.createAuthorityList(permissions);
            DtHolidayUser tUser = new DtHolidayUser(user.getId(), user.getUserName(), user.getPassword(), userAuths);
            return tUser;
        } else {
            throw new UsernameNotFoundException(BusinessErrorCodeEnum.LOGIN_ACCOUNT_NO_EXSIT.getMessageCN());
        }
    }
}