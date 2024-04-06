package com.eva.dtholiday.system.service.portalmanagement;

import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandQuotationReq;

public interface IslandQuotationService {
    /**
     * 添加一条报价信息
     *
     * @param islandQuotationReq
     */
    void islandQuotationAdd(IslandQuotationReq islandQuotationReq);
}
