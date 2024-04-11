package com.eva.dtholiday.security.service.impl;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.LoginUserReq;
import com.eva.dtholiday.commons.enums.BusinessErrorCodeEnum;
import com.eva.dtholiday.commons.exception.BusinessException;
import com.eva.dtholiday.security.service.LoginService;
import com.eva.dtholiday.security.entity.DtHolidayUser;
import com.eva.dtholiday.security.utils.JwtUtil;
import com.eva.dtholiday.security.utils.TokenCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/21 0:18
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Resource
    private JwtUtil jwtUtil;

    @Override
    public ResponseApi login(LoginUserReq loginUser) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword(), null);
        try {
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            //认证成功
            if (authentication != null) {
                //生成token
                DtHolidayUser tUser = (DtHolidayUser) authentication.getPrincipal();
                if (tUser != null) {
                    String token = jwtUtil.createToken(tUser);
                    TokenCache.putTokenUserIdCache(token, tUser);
                    return ResponseApi.ok(token);
                } else {
                    throw new BusinessException(BusinessErrorCodeEnum.USER_NOT_EXIST.getMessageCN(), BusinessErrorCodeEnum.USER_NOT_EXIST.getCode());
                }
            } else {
                throw new BusinessException(BusinessErrorCodeEnum.LOGIN_ACCOUNT_OR_PASSWORD_ERROR.getMessageCN(), BusinessErrorCodeEnum.LOGIN_ACCOUNT_OR_PASSWORD_ERROR.getCode());
            }
        }catch (Exception e){
            throw new BusinessException(BusinessErrorCodeEnum.LOGIN_ACCOUNT_OR_PASSWORD_ERROR.getMessageCN(), BusinessErrorCodeEnum.LOGIN_ACCOUNT_OR_PASSWORD_ERROR.getCode());
        }
    }
}
