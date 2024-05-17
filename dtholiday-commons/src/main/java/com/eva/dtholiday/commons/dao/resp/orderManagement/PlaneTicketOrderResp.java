package com.eva.dtholiday.commons.dao.resp.orderManagement;

import com.eva.dtholiday.commons.dao.entity.orderManagement.CustomerInfo;
import com.eva.dtholiday.commons.dao.entity.orderManagement.planeTicket.PlaneTicketInfo;
import lombok.Data;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/17 2:09
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class PlaneTicketOrderResp {

    private int planeTicketOrderId;
    private int orderType;
    private CustomerInfo customerInfo;
    private PlaneTicketInfo planeTicketInfo;
    private double initialPrice;
    private double discountPrice;
    private int currencyType;
    private int orderStatus;
    private int financialStatus;
    private String saleMan;
    private String financialMan;
    private String orderCreator;
    private Date createTime;
    private Date updateTime;
}
