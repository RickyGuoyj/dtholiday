package com.eva.dtholiday.commons.dao.resp.orderManagement;

import com.eva.dtholiday.commons.dao.entity.orderManagement.CustomerInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.transitionHotel.TransitionHotelInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/18 11:49
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class TransitionHotelOrderResp {

    private int transitionHotelOrderId;

    private int orderType;

    private CustomerInfo customerInfo;

    private TransitionHotelInfo transitionHotelInfo;

    /**
     * 订单总价
     */
    private Double totalPrice;
    /**
     * 优惠后价格
     */
    private Double discountPrice;
    /**
     * 确认号
     */
    private String bookingCode;

    private int currencyType;

    private int orderStatus;

    private int financialStatus;

    private String saleMan;

    private String financialMan;

    private String orderCreator;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 成本价
     */
    private Double costPrice;

    /**
     * 优惠金额
     */
    private Double discount;

    /**
     * 确认号
     */
    private String confirmInfo;
    /**
     * 备注
     */
    private String remarks;
}
