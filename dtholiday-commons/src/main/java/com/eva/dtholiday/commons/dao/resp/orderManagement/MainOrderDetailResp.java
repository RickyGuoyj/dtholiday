package com.eva.dtholiday.commons.dao.resp.orderManagement;

import com.eva.dtholiday.commons.dao.entity.orderManagement.TotalPriceInfo;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/26 22:29
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class MainOrderDetailResp {


    private Integer mainOrderId;

    private Timestamp createTime;

    private Timestamp updateTime;

    private String orderCreator;

    private String saleMan;

    private TotalPriceInfo totalPrice;

    private Integer islandHotelOrderId;

    private Double islandTotalPrice;

    private Double  islandDiscountPrice;

    private Integer islandCurrencyType;

    private Integer planeTicketOrderId;

    private Double planeTicketTotalPrice;

    private Double planeTicketDiscountPrice;

    private Integer planeTicketCurrencyType;

    private Integer transitionHotelOrderId;

    private Double transitionHotelTotalPrice;

    private Double transitionHotelDiscountPrice;

    private Integer transitionHotelCurrencyType;

    private Integer orderStatus;

    private Integer financialStatus;

    private Timestamp paymentTime;

    private Integer mainOrderCancelStatus;
}
