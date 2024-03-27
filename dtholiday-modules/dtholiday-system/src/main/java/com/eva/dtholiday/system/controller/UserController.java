package com.eva.dtholiday.system.controller;


import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.UserAddReq;
import com.eva.dtholiday.commons.dao.resp.UserAddResp;
import com.eva.dtholiday.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/19 14:46
 * @department evangenlion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@RestController
@RequestMapping("/erp/user")
public class UserController {

    @Autowired
    private UserService userService;


//    @PreAuthorize("hasAuthority('add_user')")
    @PostMapping("/addUser")
    public ResponseApi<UserAddResp> addUser(@RequestBody UserAddReq userAddReq) {
        UserAddResp userAddResp = userService.addUser(userAddReq);
        return ResponseApi.ok(userAddResp);
    }

//    @PreAuthorize("hasAuthority('update_user')")
    @PostMapping("/updateUser")
    public ResponseApi<UserAddResp> updateUser(@RequestBody UserAddReq userAddReq) {
        UserAddResp userAddResp = userService.addUser(userAddReq);
        return ResponseApi.ok(userAddResp);
    }

}
