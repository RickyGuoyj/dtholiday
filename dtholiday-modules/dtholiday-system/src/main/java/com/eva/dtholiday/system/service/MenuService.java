package com.eva.dtholiday.system.service;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.dto.MenuAsyncTree;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/30 12:56
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
public interface MenuService {
    ResponseApi getAllMenuList();

    MenuAsyncTree getMenuListByRoleCode(String roleCode);
}
