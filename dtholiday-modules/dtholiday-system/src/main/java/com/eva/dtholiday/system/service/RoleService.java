package com.eva.dtholiday.system.service;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.RoleReq;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/30 12:02
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
public interface RoleService {
    ResponseApi getRoleList();

    ResponseApi addRole(RoleReq roleReq);

    ResponseApi updateRole(RoleReq roleReq);

    ResponseApi getRoleInfo(RoleReq roleReq);

    ResponseApi deleteRole(RoleReq roleReq);
}
