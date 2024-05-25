package com.eva.dtholiday.commons.dao.mapper.orderManagement;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eva.dtholiday.commons.dao.entity.orderManagement.planeTicket.PlaneTicketOrder;
import org.apache.ibatis.annotations.Param;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/17 2:10
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
public interface PlaneTicketOrderMapper extends BaseMapper<PlaneTicketOrder> {
    void updateFinancialStatusById(@Param("id") int planeTicketOrderId,
                                   @Param("financialStatus") int financialStatus);
}
