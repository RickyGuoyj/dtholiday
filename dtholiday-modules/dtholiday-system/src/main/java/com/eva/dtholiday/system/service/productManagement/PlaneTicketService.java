package com.eva.dtholiday.system.service.productManagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.productManagement.PlaneTicketPageReq;
import com.eva.dtholiday.commons.dao.req.productManagement.PlaneTicketQueryReq;
import com.eva.dtholiday.commons.dao.req.productManagement.PlaneTicketReq;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/27 17:31
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
public interface PlaneTicketService {
    ResponseApi add(PlaneTicketReq req);

    ResponseApi delete(PlaneTicketQueryReq req);

    ResponseApi update(PlaneTicketReq req);

    ResponseApi queryList(PlaneTicketPageReq req);

    ResponseApi queryDetail(PlaneTicketQueryReq req);

    ResponseApi getAllAirlineCompany();

    ResponseApi queryPlaneTicketList(PlaneTicketPageReq req);
}
