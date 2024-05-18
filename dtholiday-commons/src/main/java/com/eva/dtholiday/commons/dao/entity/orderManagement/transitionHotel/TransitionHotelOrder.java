package com.eva.dtholiday.commons.dao.entity.orderManagement.transitionHotel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "transition_hotel_order_id", type = IdType.AUTO)
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
    private Double totalPrice;
    /**
     * 优惠后价格
     */
    private Double discountPrice;
    /**
     * 订单成本价
     */
    private Double costPrice;
    /**
     * 订单折扣
     */
    private Double discount;
    /**
     * 酒店预定号
     */
    private String bookingCode;
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
    /**
     * 确认号
     */
    private String confirmInfo;
    /**
     * 备注
     */
    private String remarks;
    private Date createTime;
    private Date updateTime;
}
