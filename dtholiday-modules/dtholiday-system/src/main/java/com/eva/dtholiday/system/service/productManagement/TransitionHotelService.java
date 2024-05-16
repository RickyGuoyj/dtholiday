package com.eva.dtholiday.system.service.productManagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.productManagement.PlaneTicketPageReq;
import com.eva.dtholiday.commons.dao.req.productManagement.TransitionHotelPageReq;
import com.eva.dtholiday.commons.dao.req.productManagement.TransitionHotelQueryReq;
import com.eva.dtholiday.commons.dao.req.productManagement.TransitionHotelReq;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/27 16:44
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
public interface TransitionHotelService {
    ResponseApi add(TransitionHotelReq req);

    ResponseApi delete(TransitionHotelQueryReq req);

    ResponseApi update(TransitionHotelReq req);

    ResponseApi queryList(TransitionHotelPageReq req);

    ResponseApi queryDetail(TransitionHotelQueryReq req);

    ResponseApi getAllTransitionHotel();

    ResponseApi queryTransitionHotelList(TransitionHotelPageReq req);
}
