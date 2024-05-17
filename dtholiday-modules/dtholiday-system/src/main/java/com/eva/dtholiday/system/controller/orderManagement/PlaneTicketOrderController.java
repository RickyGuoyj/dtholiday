package com.eva.dtholiday.system.controller.orderManagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.orderManagement.PlaneTicketOrderDetailReq;
import com.eva.dtholiday.commons.dao.req.orderManagement.PlaneTicketOrderPageReq;
import com.eva.dtholiday.commons.dao.req.orderManagement.PlaneTicketOrderReq;
import com.eva.dtholiday.commons.dao.resp.orderManagement.PlaneTicketOrderResp;
import com.eva.dtholiday.system.service.orderManagement.PlaneTicketOrderService;
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
@RequestMapping("/erp/orderManagement/planeTicket")
public class PlaneTicketOrderController {

    @Resource
    private PlaneTicketOrderService planeTicketOrderService;
    @PostMapping("/createPlaneTicketOrder")
    public ResponseApi createPlaneTicketOrder(@RequestBody PlaneTicketOrderReq req){
        return planeTicketOrderService.createPlaneTicketOrder(req);
    }

    @PostMapping("/updatePlaneTicketOrder")
    public ResponseApi updatePlaneTicketOrder(@RequestBody PlaneTicketOrderReq req){
        return planeTicketOrderService.updatePlaneTicketOrder(req);
    }

    @PostMapping("/queryPlaneTicketOrderList")
    public ResponseApi queryPlaneTicketOrderList(@RequestBody PlaneTicketOrderPageReq req){
        return planeTicketOrderService.queryPlaneTicketOrderList(req);
    }

    @PostMapping("/queryPlaneTicketOrderDetail")
    public ResponseApi queryPlaneTicketOrderDetail(@RequestBody PlaneTicketOrderDetailReq req){
        return planeTicketOrderService.queryPlaneTicketOrderDetail(req);
    }
}
