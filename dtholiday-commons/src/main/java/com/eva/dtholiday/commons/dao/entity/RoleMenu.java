package com.eva.dtholiday.commons.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.ToString;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/26 1:25
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@TableName("sys_role_menu")
@Data
public class RoleMenu extends Model<RoleMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编码
     */
    private Integer roleCode;

    /**
     * 菜单编码
     */
    private Integer menuCode;


    public static final String ROLE_CODE = "role_code";

    public static final String MENU_CODE = "menu_code";

}
