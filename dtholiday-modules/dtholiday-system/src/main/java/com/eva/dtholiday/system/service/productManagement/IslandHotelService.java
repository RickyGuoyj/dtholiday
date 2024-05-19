package com.eva.dtholiday.system.service.productManagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.productManagement.*;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/30 1:25
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
public interface IslandHotelService {
    ResponseApi add(IslandHotelReq req);

    ResponseApi delete(IslandHotelQueryReq req);

    ResponseApi update(IslandHotelReq req);

    ResponseApi queryList(IslandHotelPageReq req);

    ResponseApi queryDetail(IslandHotelQueryReq req);

    ResponseApi queryAllHotelList(IslandHotelQueryAllReq req);

    ResponseApi calculateIslandHotelAmount(IslandHotelCalculateReq req);
}

