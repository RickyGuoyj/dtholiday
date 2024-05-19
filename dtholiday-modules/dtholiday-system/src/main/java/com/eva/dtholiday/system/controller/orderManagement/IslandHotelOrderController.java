package com.eva.dtholiday.system.controller.orderManagement;

import com.eva.dtholiday.commons.dao.req.orderManagement.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.system.service.orderManagement.IslandHotelOrderService;

@RestController
@RequestMapping("/erp/islandHotelOrderManagement/")
public class IslandHotelOrderController {
    @Autowired
    private IslandHotelOrderService islandHotelOrderService;

    @PostMapping("/update")
    public ResponseApi addMainOrder(@RequestBody MainOrderReq req) {
        return null;
    }

    @PostMapping("/queryList")
    public ResponseApi queryIslandHotelOrderList(@RequestBody IslandHotelOrderQueryListReq req) {
        return islandHotelOrderService.queryIslandHotelOrderList(req);
    }

    @PostMapping("/queryDetail")
    public ResponseApi queryIslandHotelOrderDetail(@RequestBody IslandHotelOrderQueryDetailReq req) {
        return islandHotelOrderService.queryIslandHotelOrderDetail(req);
    }
    @PostMapping("/updateIslandHotelOrderByAgent")
    public ResponseApi updateIslandHotelOrderByAgent(@RequestBody IslandHotelOrderReq req){
        return islandHotelOrderService.updateIslandHotelOrderByAgent(req);
    }

    @PostMapping("/updateIslandHotelOrderBySaleMan")
    public ResponseApi updateIslandHotelOrderBySaleMan(@RequestBody IslandHotelOrderSalesmanReq req){
        return islandHotelOrderService.updateIslandHotelOrderBySaleMan(req);
    }

    @PostMapping("/updateIslandHotelOrderByFinancialMan")
    public ResponseApi updateIslandHotelOrderByFinancialMan(@RequestBody IslandHotelOrderFinancialManReq req){
        return islandHotelOrderService.updateIslandHotelOrderByFinancialMan(req);
    }
}
