package com.eva.dtholiday.system.service.systemmanagement.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.systemmanagement.Currency;
import com.eva.dtholiday.commons.dao.mapper.systemmanagement.CurrencyMapper;
import com.eva.dtholiday.commons.dao.req.systemmanagement.CurrencyDeleteReq;
import com.eva.dtholiday.commons.dao.req.systemmanagement.CurrencyReq;
import com.eva.dtholiday.commons.dao.resp.systemmanagement.CurrencyManagementQueryListResp;
import com.eva.dtholiday.system.service.systemmanagement.CurrencyManagementService;

@Service
public class CurrencyManagementServiceImpl implements CurrencyManagementService {
    @Resource
    private CurrencyMapper currencyMapper;

    @Override
    public ResponseApi<Integer> addCurrency(CurrencyReq req) {
        Currency currency = new Currency();
        currency.setCurrencyName(req.getCurrencyName());
        currency.setCurrencyCode(req.getCurrencyCode());
        currency.setCountryName(req.getCountryName());
        int insert = currencyMapper.insert(currency);
        return ResponseApi.ok(insert);
    }

    @Override
    public ResponseApi<Integer> updateCurrency(CurrencyReq req) {
        Currency currency = new Currency();
        currency.setCurrencyName(req.getCurrencyName());
        currency.setCurrencyCode(req.getCurrencyCode());
        currency.setCountryName(req.getCountryName());
        currency.setCurrencyIndexCode(req.getCurrencyIndexCode());
        int update = currencyMapper.updateById(currency);
        return ResponseApi.ok(update);
    }

    @Override
    public ResponseApi<Integer> deleteCurrency(CurrencyDeleteReq req) {
        int i = currencyMapper.deleteBatchIds(req.getCurrencyIndexCodeList());
        return ResponseApi.ok(i);
    }

    @Override
    public ResponseApi<CurrencyManagementQueryListResp> queryCurrencylist() {
        List<Currency> records = currencyMapper.selectList(null);
        CurrencyManagementQueryListResp resp = new CurrencyManagementQueryListResp();
        resp.setCurrencyList(records);
        return ResponseApi.ok(resp);
    }

    @Override
    public ResponseApi<Currency> queryCurrencyDetail(CurrencyReq req) {
        Currency currency = currencyMapper.selectById(req.getCurrencyIndexCode());
        return ResponseApi.ok(currency);
    }
}
