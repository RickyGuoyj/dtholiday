package com.eva.dtholiday.commons.dao.mapper.orderManagement;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eva.dtholiday.commons.dao.entity.orderManagement.islandhotelorder.IslandHotelOrder;
import com.eva.dtholiday.commons.dao.entity.orderManagement.islandhotelorder.IslandHotelOrderListInfo;

public interface IslandHotelOrderMapper extends BaseMapper<IslandHotelOrder> {
    int countIslandHotelOrderList(Map<String, Object> map);

    List<IslandHotelOrderListInfo> queryIslandHotelOrderList(Map<String, Object> map);
}
