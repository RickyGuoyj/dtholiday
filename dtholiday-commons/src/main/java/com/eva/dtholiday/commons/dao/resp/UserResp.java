package com.eva.dtholiday.commons.dao.resp;

import lombok.Data;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/19 14:50
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class UserResp {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 真实姓名
     */
    private String nickName;

    /**
     * 邮箱地址
     */
    private String emailAddr;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 性别 0女 1男
     */
    private Integer gender;

    /**
     * 角色
     */
    private RoleResp roleInfo;

    /**
     * 部门领导姓名
     */
    private String deptLeaderName;

    /**
     * 状态（0禁用 1启用）
     */
    private Integer status;

    /**
     * 公司名
     */
    private String belongCompany;

    /**
     * 公司名
     */
    private String belongCompanyPhone;




}
