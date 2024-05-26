package com.eva.dtholiday.system.controller.orderManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.financialManagement.PaymentReq;
import com.eva.dtholiday.commons.dao.req.orderManagement.MainOrderCancelReq;
import com.eva.dtholiday.commons.dao.req.orderManagement.MainOrderQueryListReq;
import com.eva.dtholiday.commons.dao.req.orderManagement.MainOrderReq;
import com.eva.dtholiday.system.service.orderManagement.MainOrderService;

@RestController
@RequestMapping("/erp/mainOrderManagement")
public class MainOrderController {
    @Autowired
    private MainOrderService mainOrderService;

    @PostMapping("/add")
    public ResponseApi addMainOrder(@RequestBody MainOrderReq req) {
        return mainOrderService.addMainOrder(req);
    }

    @PostMapping("/queryList")
    public ResponseApi queryMainOrderList(@RequestBody MainOrderQueryListReq req) {
        return mainOrderService.queryMainOrderList(req);
    }

    @PostMapping("/agentPay")
    public ResponseApi agentPay(@RequestBody PaymentReq req) {
        return mainOrderService.agentPay(req);
    }

    @PostMapping("/cancelMainOrderByAgent")
    public ResponseApi cancelMainOrderByAgent(@RequestBody MainOrderCancelReq req) {
        return mainOrderService.cancelMainOrderByAgent(req);
    }

    @PostMapping("/cancelMainOrderBySalesman")
    public ResponseApi cancelMainOrderBySalesman(@RequestBody MainOrderCancelReq req) {
        return mainOrderService.cancelMainOrderBySalesman(req);
    }

    @PostMapping("/cancelMainOrderByFinancialMan")
    public ResponseApi cancelMainOrderByFinancialMan(@RequestBody MainOrderCancelReq req) {
        return mainOrderService.cancelMainOrderByFinancialMan(req);
    }
}
