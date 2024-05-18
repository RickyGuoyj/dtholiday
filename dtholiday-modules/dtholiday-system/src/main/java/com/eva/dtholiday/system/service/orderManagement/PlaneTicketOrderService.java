package com.eva.dtholiday.system.service.orderManagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.orderManagement.*;
import com.eva.dtholiday.commons.dao.resp.orderManagement.PlaneTicketOrderResp;

import java.util.List;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/17 2:33
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
public interface PlaneTicketOrderService {
    ResponseApi createPlaneTicketOrder(PlaneTicketOrderReq req);

    ResponseApi queryPlaneTicketOrderList(PlaneTicketOrderPageReq req);

    PlaneTicketOrderResp queryPlaneTicketOrderDetail(PlaneTicketOrderDetailReq req);

    ResponseApi updatePlaneTicketOrderByFinancialMan(PlaneTicketOrderFinancialManReq req);

    ResponseApi updatePlaneTicketOrderBySaleMan(PlaneTicketOrderSalesmanReq req);

    ResponseApi updatePlaneTicketOrderByAgent(PlaneTicketOrderReq req);
}
