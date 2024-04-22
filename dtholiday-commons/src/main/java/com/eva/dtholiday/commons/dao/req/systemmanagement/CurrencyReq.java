package com.eva.dtholiday.commons.dao.req.systemmanagement;

import lombok.Data;

/**
 * 新增、修改、查询明细币种请求
 */
@Data
public class CurrencyReq {
    private Integer currencyIndexCode;
    private String currencyName;
    private String currencyCode;
    private String countryName;
}
