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

    LOGIN_ACCOUNT_NO_EXSIT("0xdcb00100", "login account no exsit", "用户不存在"),

    LOGIN_ACCOUNT_LOGIN_FAILURE("0xdcb00101", "login failure", "登录失败"),

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
