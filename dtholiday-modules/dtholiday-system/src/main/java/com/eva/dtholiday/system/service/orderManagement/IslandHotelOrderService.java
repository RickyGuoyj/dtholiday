package com.eva.dtholiday.system.service.orderManagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.orderManagement.*;

public interface IslandHotelOrderService {
    ResponseApi queryIslandHotelOrderList(IslandHotelOrderQueryListReq req);

    ResponseApi queryIslandHotelOrderDetail(IslandHotelOrderQueryDetailReq req);

    ResponseApi updateIslandHotelOrderByAgent(IslandHotelOrderReq req);

    ResponseApi updateIslandHotelOrderBySaleMan(IslandHotelOrderSalesmanReq req);

    ResponseApi updateIslandHotelOrderByFinancialMan(IslandHotelOrderFinancialManReq req);

    ResponseApi updateCheckInfo(CheckInfoReq req);
}
