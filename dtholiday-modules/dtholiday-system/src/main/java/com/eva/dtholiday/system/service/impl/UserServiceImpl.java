package com.eva.dtholiday.system.service.impl;

import com.eva.dtholiday.commons.dao.entity.User;
import com.eva.dtholiday.commons.dao.mapper.UserMapper;
import com.eva.dtholiday.commons.dao.req.UserAddReq;
import com.eva.dtholiday.commons.dao.resp.UserAddResp;
import com.eva.dtholiday.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Override
    public UserAddResp addUser(UserAddReq userAddReq) {

        User user = new User();
        BeanUtils.copyProperties(userAddReq, user);

//        userMapper.insert(user);

        UserAddResp userAddResp = new UserAddResp();
        BeanUtils.copyProperties(userAddReq, userAddResp);
        return userAddResp;
    }
}
