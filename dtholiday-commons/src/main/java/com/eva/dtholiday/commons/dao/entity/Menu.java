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
 * @create-time 2024/3/20 10:21
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名
     */
    private String menuName;

    /**
     * 菜单编码（UUID，用于绑定）
     */
    private String menuCode;

    /**
     * 父级编码（UUID，用于绑定）
     */
    private String parentCode;

    /**
     * 菜单类型  C 功能模块 F 接口权限
     */
    private String type;

    /**
     * 菜单路径
     */
    private String url;

    /**
     * 接口权限标识
     */
    private String perms;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    public static final String TYPE = "type";
}
