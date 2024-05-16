package com.eva.dtholiday.commons.dao.entity.orderManagement.transitionHotel;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/17 2:23
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dt_order_transition_hotel")
public class TransitionHotelOrder extends Model<TransitionHotelOrder> {
    private int transitionHotelOrderId;
    private int orderType;
    private String customerInfo;
    private String transitionHotelInfo;
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