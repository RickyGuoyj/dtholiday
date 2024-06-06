package com.eva.dtholiday.commons.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/19 14:37
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户编码（UUID，用于绑定）
     */
    private String userCode;

    /**
     * 密码
     */
    private String password;

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    public static final String ID = "id";

    public static final String USER_NAME = "user_name";

    public static final String NICK_NAME = "nick_name";

    public static final String USER_CODE = "user_code";

    public static final String BELONG_COMPANY = "belong_company";

    public static final String STATUS = "status";

    public static final String DEPT_LEADER_NAME = "dept_leader_name";
}
