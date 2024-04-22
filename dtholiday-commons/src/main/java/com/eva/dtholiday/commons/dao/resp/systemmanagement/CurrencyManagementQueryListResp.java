package com.eva.dtholiday.commons.dao.resp.systemmanagement;

import java.util.List;

import com.eva.dtholiday.commons.dao.entity.systemmanagement.Currency;

import lombok.Data;

@Data
public class CurrencyManagementQueryListResp {
    private List<Currency> currencyList;
}
