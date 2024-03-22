package com.eva.dtholiday.security.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/22 6:45
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
public class DtHolidayUser extends User {

    /**
     * 用户ID
     */
    @Getter
    private Integer id;

    /**
     * 前端路由集合
     */
    @Getter
    @Setter
    private String[] menuRoutes;

    /**
     * 角色集合字符
     */
    @Getter
    @Setter
    private String[] roleList;

    /**
     * 用户昵称
     */
    @Getter
    @Setter
    private String nickName;

    @Getter
    @Setter
    private LocalDateTime loginTime;



    public DtHolidayUser(Integer id, String userName, String password, Collection<? extends GrantedAuthority> authorities) {
        super(userName, password, authorities);
        this.id = id;
    }
}
