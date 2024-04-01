package com.eva.dtholiday.system.service;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.PasswordReq;
import com.eva.dtholiday.commons.dao.req.UserAddReq;
import com.eva.dtholiday.commons.dao.req.UserReq;
import com.eva.dtholiday.commons.dao.req.UserUpdateReq;

/**
 *
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/19 14:47
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
public interface UserService {

    ResponseApi addUser(UserAddReq userAddReq);

    ResponseApi getUserList();

    ResponseApi getUserInfo(String username);

    ResponseApi getCurrentUserInfo();

    ResponseApi updateUser(UserUpdateReq userUpdateReq);

    ResponseApi deleteUser(UserReq userReq);

    ResponseApi resetPwd(PasswordReq passwordReq);
}
