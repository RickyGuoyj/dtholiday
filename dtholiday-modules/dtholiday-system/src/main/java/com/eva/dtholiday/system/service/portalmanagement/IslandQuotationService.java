package com.eva.dtholiday.system.service.portalmanagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandQuotationReq;

import java.util.List;

public interface IslandQuotationService {
    public ResponseApi islandQuotationAdd(IslandQuotationReq islandQuotationReq);

    public ResponseApi islandQuotationQueryList(IslandQuotationReq islandQuotationReq);

    public ResponseApi islandQuotationQueryDetail(int quotationIndexCode);

    public ResponseApi islandQuotationUpdate(IslandQuotationReq islandQuotationReq);

    public ResponseApi islandQuotationDelete(List<Integer> quotationIndexCodeList);
}
