package com.eva.dtholiday.system.service.portalmanagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandManagementReq;

/**
 * @author fengsuohua
 */
public interface IslandManagementService {
    /**
     * 添加
     *
     * @param IslandManagementReq
     */
    void islandManagementAdd(IslandManagementReq IslandManagementReq);

    /**
     * 更新
     *
     * @param IslandManagementReq
     */
    void islandManagementUpdate(IslandManagementReq IslandManagementReq);

    /**
     * 删除
     *
     * @param islandIndexCode
     */
    void islandManagementDelete(String islandIndexCode);

    /**
     * 查询列表
     *
     * @return 响应
     */
    ResponseApi islandManagementQueryList();

    /**
     * 查询详情
     *
     * @param islandIndexCode
     * @return 响应
     */
    ResponseApi islandManagementQueryDetail(String islandIndexCode);

}
