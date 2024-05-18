package com.eva.dtholiday.commons.dao.req.orderManagement;

import com.eva.dtholiday.commons.dao.entity.orderManagement.CustomerInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.transitionHotel.TransitionHotelInfo;
import lombok.Data;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/18 0:24
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class TransitionHotelOrderReq {

    private int orderType;
    private CustomerInfo customerInfo;
    private TransitionHotelInfo transitionHotelInfo;
    private double totalPrice;
    private int currencyType;
    private String saleMan;
}
