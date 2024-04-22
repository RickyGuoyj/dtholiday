package com.eva.dtholiday.system.service.systemmanagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.systemmanagement.Currency;
import com.eva.dtholiday.commons.dao.req.systemmanagement.CurrencyDeleteReq;
import com.eva.dtholiday.commons.dao.req.systemmanagement.CurrencyReq;
import com.eva.dtholiday.commons.dao.resp.systemmanagement.CurrencyManagementQueryListResp;

public interface CurrencyManagementService {
    /**
     * 新增币种
     *
     * @param req 入参
     * @return 响应
     */
    ResponseApi<Integer> addCurrency(CurrencyReq req);

    /**
     * 更新币种
     *
     * @param req 入参
     * @return 返回
     */
    ResponseApi<Integer> updateCurrency(CurrencyReq req);

    /**
     * 删除币种
     *
     * @param req 入参
     * @return 返回
     */
    ResponseApi<Integer> deleteCurrency(CurrencyDeleteReq req);

    /**
     * 查询币种列表
     *
     * @return 返回
     */
    ResponseApi<CurrencyManagementQueryListResp> queryCurrencylist();

    /**
     * 查询币种详情
     *
     * @param req 入参
     * @return 返回
     */
    ResponseApi<Currency> queryCurrencyDetail(CurrencyReq req);
}
