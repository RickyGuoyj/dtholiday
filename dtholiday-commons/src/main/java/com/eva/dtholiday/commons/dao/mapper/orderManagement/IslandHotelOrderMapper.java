package com.eva.dtholiday.commons.dao.mapper.orderManagement;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eva.dtholiday.commons.dao.entity.orderManagement.islandhotelorder.IslandHotelOrder;
import com.eva.dtholiday.commons.dao.entity.orderManagement.islandhotelorder.IslandHotelOrderInfo;

public interface IslandHotelOrderMapper extends BaseMapper<IslandHotelOrder> {
    int countIslandHotelOrderList(Map<String, Object> map);

    List<IslandHotelOrderInfo> queryIslandHotelOrderList(Map<String, Object> map);

    IslandHotelOrderInfo queryIslandHotelOrderById(@Param("islandOrderId") Integer islandOrderId);
}
