package com.eva.dtholiday.commons.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/26 1:31
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@TableName("sys_user_role")
@Data
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编码
     */
    private Integer userCode;

    /**
     * 角色编码
     */
    private Integer roleCode;

    public static final String USER_CODE = "user_code";

    public static final String ROLE_CODE = "role_code";

}