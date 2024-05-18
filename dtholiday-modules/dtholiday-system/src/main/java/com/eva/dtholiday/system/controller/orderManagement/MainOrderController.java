package com.eva.dtholiday.system.controller.orderManagement;

import com.eva.dtholiday.commons.dao.req.orderManagement.MainOrderReq;
import com.eva.dtholiday.system.service.orderManagement.MainOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eva.dtholiday.commons.api.ResponseApi;

@RestController
@RequestMapping("/erp/mainOrderManagement")
public class MainOrderController {
    @Autowired
    private MainOrderService mainOrderService;
    @PostMapping("/add")
    public ResponseApi addMainOrder(@RequestBody MainOrderReq req){
        return mainOrderService.addMainOrder(req);
    }
}
