package com.eva.dtholiday.system.service.portalmanagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandTagReq;

public interface IslandTagService {
    /**
     * 查询单个
     *
     * @param tagIndexCode 逐渐
     * @return 结果
     */
    ResponseApi islandTagQueryDetail(int tagIndexCode);

    /**
     * 查询列表
     *
     * @return 结果
     */
    ResponseApi islandTagQueryList(IslandTagReq IslandTagReq);

    /**
     * 添加
     *
     * @param IslandTagReq
     * @return
     */
    ResponseApi islandTagAdd(IslandTagReq IslandTagReq);

    /**
     * 修改
     *
     * @param IslandTagReq
     * @return
     */
    ResponseApi islandTagUpdate(IslandTagReq IslandTagReq);

    /**
     * 删除
     *
     * @param tagIndexCode
     * @return
     */
    ResponseApi islandTagDelete(int tagIndexCode);
}
