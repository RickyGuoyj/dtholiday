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
    /**
     * 订单id
     */
    private int transitionHotelOrderId;
    /**
     * 订单类型
     */
    private int orderType;
    /**
     * 客人名称
     */
    private String customerName;
    /**
     * 成人数量
     */
    private int adultNum;
    /**
     * 孩子数量
     */
    private int childNum;
    /**
     * 房间数量
     */
    private int roomNum;
    /**
     * 间夜数
     */
    private int nights;
    /**
     * 第一个孩子的年龄
     */
    private Integer firstChildAge;
    /**
     * 第二个孩子的年龄
     */
    private Integer secondChildAge;

    /**
     * 过度酒店产品id
     */
    private int transitionHotelId;
    /**
     * 过度酒店产品名称
     */
    private String transitionHotelName;
    /**
     * 过度酒店产品房型
     */
    private String transitionHotelType;
    /**
     * 过度酒店产品生效日期
     */
    private Date effectiveDate;
    /**
     * 过度酒店产品失效日期
     */
    private Date expiryDate;
    /**
     * 订单总价
     */
    private double totalPrice;
    /**
     * 优惠后价格
     */
    private double discountPrice;
    /**
     * 订单成本价
     */
    private double costPrice;
    /**
     * 订单折扣
     */
    private double discount;
    /**
     * 订单货币类型
     */
    private int currencyType;
    /**
     * 订单状态
     */
    private int orderStatus;
    /**
     * 财务状态
     */
    private int financialStatus;
    /**
     * 销售
     */
    private String saleMan;
    /**
     * 财务
     */
    private String financialMan;
    /**
     * 订单创建人
     */
    private String orderCreator;
    private Date createTime;
    private Date updateTime;
}