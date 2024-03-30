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
     * 错误码说明 例子：0x32104444
     * 分割：0x 321 0 4444
     * 0x  :16进制标识
     * 321 :组件标识 0xd50
     * 04444 :组件错误码
     */

    UNAUTHORIZED("401", "invalid token", "Token无效或过期,请重新登录."),

    LOGIN_ACCOUNT_NO_EXIST("0xdcb00100", "login account no exsit", "用户不存在"),

    LOGIN_ACCOUNT_OR_PASSWORD_ERROR("0xdcb00101", "check username or password", "用户名或密码错误"),

    LOAD_MENU_ERROR("0xdcb00102", "load menu error", "加载菜单失败"),

    PARAMETER_CHECK_ERROR("0xdcb00103", "verify parameter error", "参数校验失败"),

    ROLE_NAME_EXIST("0xdcb00104", "role name has already existed", "角色名已存在"),

    ROLE_NOT_EXIST("0xdcb00105", "role does not exist", "角色不存在"),

    ROLE_BINDING_WITH_USER("0xdcb00106", "role is binding with user", "该角色下存在用户，无法删除"),

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
