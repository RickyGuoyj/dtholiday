package com.eva.dtholiday.commons.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
     * 密码
     */
    private String password;

    /**
     * 角色集合
     */
    @TableField(exist = false)
    private Integer[] roles;

    /**
     * 角色集合字符
     */
    @TableField(exist = false)
    private String[] roleList;
    /**
     * 权限标识集合
     */
    @TableField(exist = false)
    private String[] permissions;

    /**
     * 前端路由集合
     */
    @TableField(exist = false)
    private String[] menuRoutes;

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
}
