package com.eva.dtholiday.commons.dao.resp.portalmanagement;

import java.util.List;

import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandQuotation;

import lombok.Data;

@Data
public class IslandQuotationQueryListResp {
    private int page;
    private int pageSize;
    private List<IslandQuotation> islandQuotationList;
}
