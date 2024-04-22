package com.eva.dtholiday.system.service.systemmanagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.systemmanagement.Traffic;
import com.eva.dtholiday.commons.dao.req.systemmanagement.TrafficDeleteReq;
import com.eva.dtholiday.commons.dao.req.systemmanagement.TrafficReq;
import com.eva.dtholiday.commons.dao.resp.systemmanagement.TrafficManagementQueryListResp;

public interface TrafficManagementService {
    /**
     * 新增交通工具
     *
     * @param req 入参
     * @return 响应
     */
    ResponseApi<Integer> addTraffic(TrafficReq req);

    /**
     * 更新交通工具
     *
     * @param req 入参
     * @return 返回
     */
    ResponseApi<Integer> updateTraffic(TrafficReq req);

    /**
     * 删除交通工具
     *
     * @param req 入参
     * @return 返回
     */
    ResponseApi<Integer> deleteTraffic(TrafficDeleteReq req);

    /**
     * 查询交通工具列表
     *
     * @return 返回
     */
    ResponseApi<TrafficManagementQueryListResp> queryTrafficlist();

    /**
     * 查询交通工具详情
     *
     * @param req 入参
     * @return 返回
     */
    ResponseApi<Traffic> queryTrafficDetail(TrafficReq req);
}
