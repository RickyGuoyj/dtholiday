package com.eva.dtholiday.system.controller.orderManagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.orderManagement.*;
import com.eva.dtholiday.system.service.orderManagement.TransitionHotelOrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/17 2:31
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@RestController
@RequestMapping("/erp/orderManagement/transitionHotel")
public class TransitionHotelOrderController {

    @Resource
    private TransitionHotelOrderService transitionHotelOrderService;
    @PostMapping("/createTransitionHotelOrder")
    public ResponseApi createTransitionHotelOrder(@RequestBody TransitionHotelOrderReq req){
        return transitionHotelOrderService.createTransitionHotelOrder(req);
    }

    @PostMapping("/updateTransitionHotelOrderByAgent")
    public ResponseApi updateTransitionHotelOrderByAgent(@RequestBody TransitionHotelOrderReq req){
        return transitionHotelOrderService.updateTransitionHotelOrderByAgent(req);
    }

    @PostMapping("/updateTransitionHotelOrderBySaleMan")
    public ResponseApi updateTransitionHotelOrderBySaleMan(@RequestBody TransitionHotelOrderSalesmanReq req){
        return transitionHotelOrderService.updateTransitionHotelOrderBySaleMan(req);
    }

    @PostMapping("/updateTransitionHotelOrderByFinancialMan")
    public ResponseApi updateTransitionHotelOrderByFinancialMan(@RequestBody TransitionHotelOrderFinancialManReq req){
        return transitionHotelOrderService.updateTransitionHotelOrderByFinancialMan(req);
    }

    @PostMapping("/queryTransitionHotelOrderList")
    public ResponseApi queryTransitionHotelOrderList(@RequestBody TransitionHotelOrderPageReq req){
        return ResponseApi.ok(transitionHotelOrderService.queryTransitionHotelOrderList(req));
    }

    @PostMapping("/queryTransitionHotelOrderDetail")
    public ResponseApi queryTransitionHotelOrderDetail(@RequestBody TransitionHotelOrderDetailReq req){
        return ResponseApi.ok(transitionHotelOrderService.queryTransitionHotelOrderDetail(req));
    }
}
