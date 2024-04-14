package com.eva.dtholiday.commons.dao.resp.portalmanagement;

import java.util.List;

import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandManagementQuotation;

import lombok.Data;

@Data
public class IslandQuotationQueryListResp {
    private int page;
    private int pageSize;
    private List<IslandManagementQuotation> islandQuotationList;
}
