package com.eva.dtholiday.commons.dao.req;

import lombok.Getter;

import java.util.List;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/31 21:40
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Getter
public class UserReq {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 姓名
     */
    private String nickname;

    /**
     * 角色编码
     */
    private List<String> roleCode;

    /**
     * 所属公司
     */
    private String belongCompany;

    /**
     * 用户名
     */
    private List<String> userNames;

    /**
     * 用户名
     */
    private Integer status;

}
