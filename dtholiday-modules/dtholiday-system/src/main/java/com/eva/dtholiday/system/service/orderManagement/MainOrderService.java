package com.eva.dtholiday.system.service.orderManagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.orderManagement.MainOrderReq;

public interface MainOrderService {
    ResponseApi addMainOrder(MainOrderReq req);
}
