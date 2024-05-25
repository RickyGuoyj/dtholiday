package com.eva.dtholiday.system.controller;


import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.PasswordReq;
import com.eva.dtholiday.commons.dao.req.UserAddReq;
import com.eva.dtholiday.commons.dao.req.UserReq;
import com.eva.dtholiday.commons.dao.req.UserUpdateReq;
import com.eva.dtholiday.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/getUserList")
    public ResponseApi getUserList(@RequestBody UserReq userReq) {
        return ResponseApi.ok(userService.getUserList(userReq));
    }

    @PostMapping("/getUserInfo")
    public ResponseApi getUserInfo(@RequestBody UserReq userReq) {
        return userService.getUserInfo(userReq.getUserName());
    }

    @GetMapping("/getCurrentUserInfo")
    public ResponseApi getCurrentUserInfo() {
        return userService.getCurrentUserInfo();
    }

    //    @PreAuthorize("hasAuthority('add_user')")
    @PostMapping("/addUser")
    public ResponseApi addUser(@RequestBody UserAddReq userAddReq) {
        return userService.addUser(userAddReq);
    }

    //    @PreAuthorize("hasAuthority('update_user')")
    @PostMapping("/updateUser")
    public ResponseApi updateUser(@RequestBody UserUpdateReq userUpdateReq) {
        return userService.updateUser(userUpdateReq);
    }

    @PostMapping("/deleteUser")
    public ResponseApi deleteUser(@RequestBody UserReq userReq) {
        return userService.deleteUser(userReq);
    }

    @PostMapping("/resetPwd")
    public ResponseApi resetPwd(@RequestBody PasswordReq passwordReq) {
        return userService.resetPwd(passwordReq);
    }

    @PostMapping("/changeStatus")
    public ResponseApi changeStatus(@RequestBody UserReq userReq) {
        return userService.changeStatus(userReq);
    }

    @PostMapping("/getUserListBySaleManRoleType")
    public ResponseApi getUserListBySaleManRoleType(@RequestBody UserReq userReq) {
        return ResponseApi.ok(userService.getUserListBySaleManRoleType(userReq));
    }

    @PostMapping("/getUserListByFinancialManRoleType")
    public ResponseApi getUserListByFinancialManRoleType(@RequestBody UserReq userReq) {
        return ResponseApi.ok(userService.getUserListByFinancialManRoleType(userReq));
    }

    @PostMapping("/getAllCompanyName")
    public ResponseApi getAllCompanyName() {
        return ResponseApi.ok(userService.getAllCompanyName());
    }

}
