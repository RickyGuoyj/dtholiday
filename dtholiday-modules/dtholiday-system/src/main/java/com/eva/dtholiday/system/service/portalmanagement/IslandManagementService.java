package com.eva.dtholiday.system.service.portalmanagement;

import java.util.List;

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
    ResponseApi islandManagementAdd(IslandManagementReq IslandManagementReq);

    /**
     * 更新
     *
     * @param IslandManagementReq
     */
    ResponseApi islandManagementUpdate(IslandManagementReq IslandManagementReq);

    /**
     * 删除
     *
     * @param islandIndexCode
     */
    ResponseApi islandManagementDelete(List<Integer> islandIndexCodeList);

    /**
     * 查询列表
     *
     * @return 响应
     */
    ResponseApi islandManagementQueryList(IslandManagementReq islandManagementReq);

    /**
     * 查询详情
     *
     * @param islandIndexCode
     * @return 响应
     */
    ResponseApi islandManagementQueryDetail(int islandIndexCode);

}
