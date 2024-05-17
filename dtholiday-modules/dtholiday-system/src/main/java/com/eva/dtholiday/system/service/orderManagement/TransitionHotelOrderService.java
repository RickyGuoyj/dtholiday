package com.eva.dtholiday.system.service.orderManagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.orderManagement.TransitionHotelOrderDetailReq;
import com.eva.dtholiday.commons.dao.req.orderManagement.TransitionHotelOrderPageReq;
import com.eva.dtholiday.commons.dao.req.orderManagement.TransitionHotelOrderReq;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/17 2:34
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
public interface TransitionHotelOrderService {
    ResponseApi createTransitionHotelOrder(TransitionHotelOrderReq req);

    ResponseApi updateTransitionHotelOrder(TransitionHotelOrderReq req);

    ResponseApi queryTransitionHotelOrderList(TransitionHotelOrderPageReq req);

    ResponseApi queryTransitionHotelOrderDetail(TransitionHotelOrderDetailReq req);
}
