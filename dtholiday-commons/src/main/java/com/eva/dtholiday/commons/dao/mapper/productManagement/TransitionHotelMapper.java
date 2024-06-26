package com.eva.dtholiday.commons.dao.mapper.productManagement;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eva.dtholiday.commons.dao.entity.productManagement.TransitionHotel;

import java.util.List;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/27 20:59
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
public interface TransitionHotelMapper extends BaseMapper<TransitionHotel> {
    void batchInsert(List<TransitionHotel> transitionHotelList);
}
