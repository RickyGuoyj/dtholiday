package com.eva.dtholiday.commons.dao.req.systemmanagement;

import lombok.Data;

import java.util.List;

@Data
public class CurrencyDeleteReq {
    private List<Integer> currencyIndexCodeList;
}
