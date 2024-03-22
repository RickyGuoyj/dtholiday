package com.eva.dtholiday.security.controller;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.LoginUserReq;
import com.eva.dtholiday.security.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/19 14:34
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@RestController
//@RequestMapping("/auth")
public class LoginController {


    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseApi login(@RequestBody LoginUserReq loginUser) {

        return loginService.login(loginUser);
    }
}

