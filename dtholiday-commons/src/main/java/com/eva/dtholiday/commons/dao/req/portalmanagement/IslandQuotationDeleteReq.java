package com.eva.dtholiday.commons.dao.req.portalmanagement;

import lombok.Data;

import java.util.List;

@Data
public class IslandQuotationDeleteReq {
    List<Integer> quotationIndexCodeList;
}
