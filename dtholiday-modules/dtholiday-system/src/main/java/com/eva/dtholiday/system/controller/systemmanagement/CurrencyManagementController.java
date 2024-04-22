package com.eva.dtholiday.system.controller.systemmanagement;

import com.eva.dtholiday.commons.dao.req.systemmanagement.CurrencyDeleteReq;
import com.eva.dtholiday.system.service.systemmanagement.CurrencyManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.systemmanagement.CurrencyReq;

@RestController
@RequestMapping("/erp/systemManagement/currencyManagement")
public class CurrencyManagementController {

    @Autowired
    private CurrencyManagementService currencyManagementService;

    @PostMapping("/add")
    public ResponseApi addCurrency(@RequestBody CurrencyReq req) {
        return currencyManagementService.addCurrency(req);
    }

    @PostMapping("/update")
    public ResponseApi updateCurrency(@RequestBody CurrencyReq req) {
        return currencyManagementService.updateCurrency(req);
    }

    @PostMapping("/delete")
    public ResponseApi deleteCurrency(@RequestBody CurrencyDeleteReq req) {
        return currencyManagementService.deleteCurrency(req);
    }

    @PostMapping("/querylist")
    public ResponseApi queryCurrencylist() {
        return currencyManagementService.queryCurrencylist();
    }

    @PostMapping("/querydetail")
    public ResponseApi queryCurrencyDetail(@RequestBody CurrencyReq req) {
        return currencyManagementService.queryCurrencyDetail(req);
    }
}
