package com.eva.dtholiday.system.service.orderManagement.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.orderManagement.islandhotelorder.IslandHotelOrderListInfo;
import com.eva.dtholiday.commons.dao.mapper.orderManagement.IslandHotelOrderMapper;
import com.eva.dtholiday.commons.dao.req.orderManagement.IslandHotelOrderQueryListReq;
import com.eva.dtholiday.commons.dao.resp.orderManagement.IslandHotelOrderQueryListResp;
import com.eva.dtholiday.system.service.orderManagement.IslandHotelOrderService;

@Service
public class IslandHotelOrderServiceImpl implements IslandHotelOrderService {

    @Autowired
    private IslandHotelOrderMapper islandHotelOrderMapper;

    public ResponseApi queryIslandHotelOrderList(IslandHotelOrderQueryListReq req) {
        Map<String, Object> map = new HashMap<>();
        map.put("customerName", req.getCustomerName());
        map.put("islandCnName", req.getIslandCnName());
        map.put("orderStatus", req.getOrderStatus());
        map.put("financialStatus", req.getFinancialStatus());
        int count = islandHotelOrderMapper.countIslandHotelOrderList(map);
        map.put("from", (req.getPage() - 1) * req.getPageSize());
        map.put("to", req.getPageSize());
        List<IslandHotelOrderListInfo> islandHotelOrderListInfos =
            islandHotelOrderMapper.queryIslandHotelOrderList(map);
        if (Objects.nonNull(islandHotelOrderListInfos)) {
            IslandHotelOrderQueryListResp islandHotelOrderQueryListResp = new IslandHotelOrderQueryListResp();
            islandHotelOrderQueryListResp.setTotal(count);
            islandHotelOrderQueryListResp.setPage(req.getPage());
            islandHotelOrderQueryListResp.setPageSize(req.getPageSize());
            islandHotelOrderQueryListResp.setIslandHotelOrderListInfoList(islandHotelOrderListInfos);
            return ResponseApi.ok(islandHotelOrderQueryListResp);
        }
        return ResponseApi.ok();
    }
}