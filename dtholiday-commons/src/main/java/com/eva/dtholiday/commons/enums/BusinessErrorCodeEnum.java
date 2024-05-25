package com.eva.dtholiday.commons.enums;

import lombok.Getter;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/20 10:39
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Getter
public enum BusinessErrorCodeEnum {
    /**
     * 错误码说明 例子：0x32104444 分割：0x 321 0 4444 0x :16进制标识 321 :组件标识 0xd50 04444 :组件错误码
     */
    SUCCESS("0", "SUCCESS", "成功"),

    FAIL("1", "FAIL", "系统异常"),

    /***************************************************** 用户模块 ：0xdcb001XX ****************************************/

    UNAUTHORIZED("401", "invalid token", "Token无效或过期,请重新登录."),

    USER_NOT_EXIST("0xdcb00100", "user does not exist", "用户不存在"),

    LOGIN_ACCOUNT_OR_PASSWORD_ERROR("0xdcb00101", "check username or password", "用户名或密码错误"),

    LOAD_MENU_ERROR("0xdcb00102", "load menu error", "加载菜单失败"),

    PARAMETER_CHECK_ERROR("0xdcb00103", "verify parameter error", "参数校验失败"),

    ROLE_NAME_EXIST("0xdcb00104", "role name has already existed", "角色名已存在"),

    USER_NAME_EXIST("0xdcb00105", "user name has already existed", "用户名已存在"),

    ROLE_NOT_EXIST("0xdcb00106", "role does not exist", "角色不存在"),

    ROLE_BINDING_WITH_USER("0xdcb00107", "role is binding with user", "该角色下存在用户，无法删除"),

    PASSWORD_CHECK_ERROR("0xdcb00108", "check password error", "两次密码不一致"),

    OLD_PASSWORD_CHECK_ERROR("0xdcb00109", "check old password error", "旧密码错误"),

    USER_IS_ALIVE("0xdcb00110", "use is alive", "用户正在登录中，无法对其操作"),

    USER_IS_DISABLED("0xdcb00111", "use is disabled", "用户已被禁用"),


    /***************************************************** 前台模块 ：0xdcb002XX ****************************************/

    ISLAND_MANAGEMENT_ADD_FAIL("0xdcb00201", "island management add fail", "岛屿管理添加失败"),

    ISLAND_MANAGEMENT_UPDATE_FAIL("0xdcb00202", "island management update fail", "岛屿管理更新失败"),

    ISLAND_MANAGEMENT_DELETE_FAIL("0xdcb00203", "island management delete fail", "岛屿管理删除失败"),

    ISLAND_NAME_HAS_EXISTED("0xdcb00204", "island name has existed", "岛屿名称已存在"),

    ISLAND_NOT_EXISTED("0xdcb00205", "island dose not existed", "岛屿不存在"),

    ISLAND_TAG_NOT_EXISTED("0xdcb00206", "island tag dose not existed", "岛屿标签不存在"),

    ISLAND_TAG_NAME_HAS_EXISTED("0xdcb00206", "island tag has existed", "岛屿标签名称已存在"),
    ;

    /**
     * 响应码--code
     **/
    private String code;
    /**
     * 响应信息--message
     **/
    private String message;
    /**
     * 响应信息--messageCn
     **/
    private String messageCN;

    BusinessErrorCodeEnum(String code, String message, String messageCN) {
        this.code = code;
        this.message = message;
        this.messageCN = messageCN;
    }
}
