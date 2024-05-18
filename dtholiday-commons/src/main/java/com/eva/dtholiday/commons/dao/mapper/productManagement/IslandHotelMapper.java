package com.eva.dtholiday.commons.dao.mapper.productManagement;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eva.dtholiday.commons.dao.entity.productManagement.IslandHotel;
import com.eva.dtholiday.commons.dao.entity.productManagement.IslandHotelMainOrder;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/30 1:30
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
public interface IslandHotelMapper extends BaseMapper<IslandHotel> {
    List<IslandHotelMainOrder> queryAllHotelInfo(Map<String,Object> map);
}
