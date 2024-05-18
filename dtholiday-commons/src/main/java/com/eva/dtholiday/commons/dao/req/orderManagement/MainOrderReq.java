package com.eva.dtholiday.commons.dao.req.orderManagement;

import com.eva.dtholiday.commons.dao.entity.orderManagement.TotalPriceInfo;
import lombok.Data;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/17 2:11
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class MainOrderReq {

    private PlaneTicketOrderReq planeTicketOrder;

    private TransitionHotelOrderReq transitionHotelOrder;

    private IslandHotelOrderReq islandHotelOrder;

    private TotalPriceInfo totalPrice;

    private String saleMan;

    private Integer orderType;
}
