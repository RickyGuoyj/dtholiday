package com.eva.dtholiday.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.Role;
import com.eva.dtholiday.commons.dao.entity.User;
import com.eva.dtholiday.commons.dao.entity.UserRole;
import com.eva.dtholiday.commons.dao.mapper.RoleMapper;
import com.eva.dtholiday.commons.dao.mapper.UserMapper;
import com.eva.dtholiday.commons.dao.mapper.UserRoleMapper;
import com.eva.dtholiday.commons.dao.req.*;
import com.eva.dtholiday.commons.dao.resp.RoleResp;
import com.eva.dtholiday.commons.dao.resp.UserResp;
import com.eva.dtholiday.commons.enums.BusinessErrorCodeEnum;
import com.eva.dtholiday.commons.exception.BusinessException;
import com.eva.dtholiday.commons.utils.MyStringUtils;
import com.eva.dtholiday.security.entity.DtHolidayUser;
import com.eva.dtholiday.security.utils.TokenCache;
import com.eva.dtholiday.system.service.RoleService;
import com.eva.dtholiday.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator evangelion
 * @create-time 2024/3/19 14:48
 * @department 睿影科技
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleService roleService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public ResponseApi getUserList(UserReq userReq) {
        List<User> userList = new ArrayList<>();
        // 如果用户名不为空，则根据用户名模糊匹配用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(userReq.getUserName())) {
            userQueryWrapper.like(User.USER_NAME, userReq.getUserName());
        }
        if (StringUtils.hasText(userReq.getNickName())) {
            userQueryWrapper.like(User.NICK_NAME, userReq.getNickName());
        }
        if (StringUtils.hasText(userReq.getBelongCompany())) {
            userQueryWrapper.like(User.BELONG_COMPANY, userReq.getBelongCompany());
        }

        userList = userMapper.selectList(userQueryWrapper);
        // 如果角色不为空，则根据角色找用户编码
        if (!CollectionUtils.isEmpty(userReq.getRoleCode())) {
            QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
            userRoleQueryWrapper.in(UserRole.ROLE_CODE, userReq.getRoleCode());
            List<UserRole> userRoleList = userRoleMapper.selectList(userRoleQueryWrapper);
            // 获取用户编码
            List<String> inRoleUserList = userRoleList.stream().map(UserRole::getUserCode).collect(Collectors.toList());
            // 筛出用户编码对应的用户
            userList = userList.stream().filter(user -> inRoleUserList.contains(user.getUserCode())).collect(Collectors.toList());
        }

        if (CollectionUtils.isEmpty(userList)) {
            return ResponseApi.ok();
        }
        //通过username去获取用户详细信息，并组装成list
        List<UserResp> userRespList = userList.stream().map(this::getUserDetail).collect(Collectors.toList());
        return ResponseApi.ok(userRespList);
    }

    private UserResp getUserDetail(User user) {
        UserResp userResp = new UserResp();
        //根据用户名获取用户
        BeanUtils.copyProperties(user, userResp);
        List<Role> roleList = roleMapper.selectRoleByUserCode(user.getUserCode());
        if (!CollectionUtils.isEmpty(roleList)) {
            RoleReq roleReq = new RoleReq();
            roleReq.setCode(roleList.get(0).getRoleCode());
            RoleResp roleResp = roleService.getRoleInfo(roleReq);
            userResp.setRoleInfo(roleResp);
        }
        return userResp;
    }

    @Override
    public ResponseApi getUserInfo(String username) {
        if (!StringUtils.hasText(username)) {
            throw new BusinessException(BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getMessageCN(), BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getCode());
        }
        UserResp userResp = getUserDetail(username);
        return ResponseApi.ok(userResp);
    }

    @Override
    public ResponseApi getCurrentUserInfo() {
        Principal principal = httpServletRequest.getUserPrincipal();
        if (principal == null) {
            return ResponseApi.unAuth("未登录");
        }
        String username = principal.getName();
        UserResp userResp = getUserDetail(username);
        return ResponseApi.ok(userResp);
    }

    private UserResp getUserDetail(String userName) {
        UserResp userResp = new UserResp();
        //根据用户名获取用户
        User user = userMapper.selectOne(new QueryWrapper<User>().eq(User.USER_NAME, userName));
        if (Objects.isNull(user)) {
            throw new BusinessException(BusinessErrorCodeEnum.USER_NOT_EXIST.getMessageCN(), BusinessErrorCodeEnum.USER_NOT_EXIST.getCode());
        }
        BeanUtils.copyProperties(user, userResp);
        List<Role> roleList = roleMapper.selectRoleByUserCode(user.getUserCode());
        if (!CollectionUtils.isEmpty(roleList)) {
            RoleReq roleReq = new RoleReq();
            roleReq.setCode(roleList.get(0).getRoleCode());
            RoleResp roleResp = roleService.getRoleInfo(roleReq);
            userResp.setRoleInfo(roleResp);
        }
        return userResp;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi addUser(UserAddReq userAddReq) {

        // 参数校验，用户名和密码不能为空
        if (!StringUtils.hasText(userAddReq.getUserName()) || !StringUtils.hasText(userAddReq.getPassword()) || !StringUtils.hasText(userAddReq.getConfirmPwd())) {
            throw new BusinessException(BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getMessageCN(), BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getCode());
        }
        // 参数校验，密码一致性校验
        if (!userAddReq.getConfirmPwd().equals(userAddReq.getPassword())) {
            throw new BusinessException(BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getMessageCN(), BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getCode());
        }
        //用户同名校验
        User oldUser = userMapper.selectOne(new QueryWrapper<User>().eq(User.USER_NAME, userAddReq.getUserName()));
        if (Objects.nonNull(oldUser)) {
            throw new BusinessException(BusinessErrorCodeEnum.USER_NAME_EXIST.getMessageCN(), BusinessErrorCodeEnum.USER_NAME_EXIST.getCode());
        }

        User user = new User();
        BeanUtils.copyProperties(userAddReq, user);
        user.setUserCode(MyStringUtils.getUUID());
        user.setPassword(passwordEncoder.encode(userAddReq.getPassword()));

        UserRole userRole = new UserRole();
        userRole.setUserCode(user.getUserCode());
        userRole.setRoleCode(userAddReq.getRoleCode());

        userMapper.insert(user);
        userRoleMapper.insert(userRole);

        UserResp userResp = getUserDetail(user);
        return ResponseApi.ok(userResp);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi updateUser(UserUpdateReq userUpdateReq) {
        // 参数校验，用户名和用户编码不能为空
        if (!StringUtils.hasText(userUpdateReq.getUserName()) || !StringUtils.hasText(userUpdateReq.getUserCode())) {
            throw new BusinessException(BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getMessageCN(), BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getCode());
        }
        // 用户存在性校验
        User oldUser = userMapper.selectOne(new QueryWrapper<User>().eq(User.USER_CODE, userUpdateReq.getUserCode()));
        if (Objects.isNull(oldUser)) {
            throw new BusinessException(BusinessErrorCodeEnum.USER_NOT_EXIST.getMessageCN(), BusinessErrorCodeEnum.USER_NOT_EXIST.getCode());
        }
        if (!userUpdateReq.getUserName().equals(oldUser.getUserName())) {
            // 校验新用户名是否重名
            User nameUser = userMapper.selectOne(new QueryWrapper<User>().eq(User.USER_NAME, userUpdateReq.getUserName()));
            if (Objects.nonNull(nameUser) && !nameUser.getUserCode().equals(userUpdateReq.getUserCode())) {
                throw new BusinessException(BusinessErrorCodeEnum.USER_NAME_EXIST.getMessageCN(), BusinessErrorCodeEnum.USER_NAME_EXIST.getCode());
            }
            // 校验用户是否正在登录，如果登录则不让修改
            List<DtHolidayUser> aliveUser = TokenCache.getAllAliveUser();
            if (aliveUser.stream().anyMatch(user -> user.getUsername().equals(oldUser.getUserName()))) {
                throw new BusinessException(BusinessErrorCodeEnum.USER_IS_ALIVE.getMessageCN(), BusinessErrorCodeEnum.USER_IS_ALIVE.getCode());
            }
        }
        // 如果用户的角色与之前不同，则修改用户角色对应关系
        List<Role> roleList = roleMapper.selectRoleByUserCode(userUpdateReq.getUserCode());
        // 如果之前无角色，现在有则添加
        if (CollectionUtils.isEmpty(roleList) && StringUtils.hasText(userUpdateReq.getRoleCode())) {
            UserRole userRole = new UserRole();
            userRole.setUserCode(userUpdateReq.getUserCode());
            userRole.setRoleCode(userUpdateReq.getRoleCode());
            userRoleMapper.insert(userRole);
        }
        // 如果之前有，则对比，替换，不需要校验RoleCode的真伪性，如果RoleCode是假的，那他都拿到超管权限了干啥不好
        if (!CollectionUtils.isEmpty(roleList) && !roleList.get(0).getRoleCode().equals(userUpdateReq.getRoleCode())) {
            // 删除用户角色对应关系
            userRoleMapper.delete(new QueryWrapper<UserRole>().eq(UserRole.USER_CODE, userUpdateReq.getUserCode()));
            if (StringUtils.hasText(userUpdateReq.getRoleCode())) {
                // 添加用户角色对应关系
                UserRole userRole = new UserRole();
                userRole.setUserCode(userUpdateReq.getUserCode());
                userRole.setRoleCode(userUpdateReq.getRoleCode());
                userRoleMapper.insert(userRole);
            }
        }
        BeanUtils.copyProperties(userUpdateReq, oldUser);
        userMapper.updateById(oldUser);
        return ResponseApi.ok(getUserDetail(oldUser.getUserName()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseApi deleteUser(UserReq userReq) {
        // 校验用户是否正在登录，如果登录则不让删
        List<DtHolidayUser> aliveUser = TokenCache.getAllAliveUser();
        if (!CollectionUtils.isEmpty(userReq.getUserNames())) {
            if (aliveUser.stream().anyMatch(user -> userReq.getUserNames().contains(user.getUsername()))) {
                throw new BusinessException(BusinessErrorCodeEnum.USER_IS_ALIVE.getMessageCN(), BusinessErrorCodeEnum.USER_IS_ALIVE.getCode());
            }
        }
        User user = userMapper.selectOne(new QueryWrapper<User>().eq(User.USER_NAME, userReq.getUserName()));
        if (Objects.nonNull(user)) {
            userMapper.delete(new QueryWrapper<User>().eq(User.USER_NAME, userReq.getUserName()));
            userRoleMapper.delete(new QueryWrapper<UserRole>().eq(UserRole.USER_CODE, user.getUserCode()));
        }
        UserResp userResp = new UserResp();
        if (Objects.nonNull(user)){
            userResp.setUserName(user.getUserName());
        }
        return ResponseApi.ok(userResp);
    }

    @Override
    public ResponseApi resetPwd(PasswordReq passwordReq) {
        // 参数校验，用户名和密码不能为空
        if (!StringUtils.hasText(passwordReq.getUserName()) || !StringUtils.hasText(passwordReq.getOldPwd()) || !StringUtils.hasText(passwordReq.getNewPwd())
                || !StringUtils.hasText(passwordReq.getConfirmPwd())) {
            throw new BusinessException(BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getMessageCN(), BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getCode());
        }
        // 用户存在性校验
        User oldUser = userMapper.selectOne(new QueryWrapper<User>().eq(User.USER_NAME, passwordReq.getUserName()));
        if (Objects.isNull(oldUser)) {
            throw new BusinessException(BusinessErrorCodeEnum.USER_NOT_EXIST.getMessageCN(), BusinessErrorCodeEnum.USER_NOT_EXIST.getCode());
        }
        // 用户存在则校验旧密码是否一致
        if (!passwordEncoder.matches(passwordReq.getOldPwd(), oldUser.getPassword())) {
            throw new BusinessException(BusinessErrorCodeEnum.OLD_PASSWORD_CHECK_ERROR.getMessageCN(), BusinessErrorCodeEnum.OLD_PASSWORD_CHECK_ERROR.getCode());
        }

        // 参数校验，密码一致性校验
        if (!passwordReq.getConfirmPwd().equals(passwordReq.getNewPwd())) {
            throw new BusinessException(BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getMessageCN(), BusinessErrorCodeEnum.PARAMETER_CHECK_ERROR.getCode());
        }
        oldUser.setPassword(passwordEncoder.encode(passwordReq.getNewPwd()));
        userMapper.updateById(oldUser);
        return ResponseApi.ok("密码修改成功");
    }

    @Override
    public ResponseApi changeStatus(UserReq userReq) {
        if (StringUtils.hasText(userReq.getUserName()) && userReq.getStatus() != null) {
            User user = userMapper.selectOne(new QueryWrapper<User>().eq(User.USER_NAME, userReq.getUserName()));
            if (Objects.nonNull(user)) {
                user.setStatus(userReq.getStatus());
                userMapper.updateById(user);
                return ResponseApi.ok("状态修改成功");
            }
        }
        return ResponseApi.error("状态修改失败");
    }
}
