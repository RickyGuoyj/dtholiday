package com.eva.dtholiday.commons.dao.mapper.orderManagement;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eva.dtholiday.commons.dao.entity.orderManagement.mainorder.MainOrderListInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.mainorder.MainOrder;

public interface MainOrderMapper extends BaseMapper<MainOrder> {
    List<MainOrderListInfo> queryMainOrderList(Map<String, Object> map);

    int countMainOrderList(Map<String, Object> map);
}
