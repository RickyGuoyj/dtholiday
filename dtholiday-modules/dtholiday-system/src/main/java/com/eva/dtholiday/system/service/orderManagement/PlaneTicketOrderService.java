package com.eva.dtholiday.system.service.orderManagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.orderManagement.PlaneTicketOrderDetailReq;
import com.eva.dtholiday.commons.dao.req.orderManagement.PlaneTicketOrderPageReq;
import com.eva.dtholiday.commons.dao.req.orderManagement.PlaneTicketOrderReq;
import com.eva.dtholiday.commons.dao.resp.orderManagement.PlaneTicketOrderResp;

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

    ResponseApi updatePlaneTicketOrder(PlaneTicketOrderReq req);

    ResponseApi queryPlaneTicketOrderList(PlaneTicketOrderPageReq req);

    ResponseApi queryPlaneTicketOrderDetail(PlaneTicketOrderDetailReq req);
}
