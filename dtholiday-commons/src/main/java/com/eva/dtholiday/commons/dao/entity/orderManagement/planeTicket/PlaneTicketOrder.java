package com.eva.dtholiday.commons.dao.entity.orderManagement.planeTicket;

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
 * @create-time 2024/5/17 1:10
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dt_order_plane_ticket")
public class PlaneTicketOrder extends Model<PlaneTicketOrder> {
    private static final long serialVersionUID = 1L;

    /**
     * 机票订单id
     */
    @TableId(value = "plane_ticket_order_id", type = IdType.AUTO)
    private int planeTicketOrderId;
    /**
     * 订单类型
     */
    private int orderType;
    /**
     * 客户信息
     */
    private String customerName;
    /**
     * 成人数量
     */
    private int adultNum;
    /**
     * 儿童数量
     */
    private int childNum;
    /**
     * 机票id
     */
    private int planeTicketId;
    /**
     * 航空公司名称
     */
    private String airlineCompanyName;
    /**
     * 出发日期
     */
    private Date departureDate;
    /**
     * 返程日期
     */
    private Date returnDate;
    /**
     * 航班天数
     */
    private String days;
    /**
     * 出发航班
     */
    private String departureFlight;
    /**
     * 返程航班
     */
    private String returnFlight;
    /**
     * 出发地点
     */
    private String departurePlace;
    /**
     * 到达地点
     */
    private String arrivalPlace;
    /**
     * 机票价格
     */
    private double price;
    /**
     * 货币类型
     */
    private int currencyType;
    /**
     * 机票订单总价格
     */
    private double totalPrice;
    /**
     * 优惠后价格
     */
    private double discountPrice;
    /**
     * 成本价
     */
    private double costPrice;
    /**
     * 折扣金额
     */
    private double discount;
    /**
     * 机票号
     */
    private String ticketNumber;
    /**
     * 订单状态
     */
    private int orderStatus;
    /**
     * 财务状态
     */
    private int financialStatus;
    /**
     * 销售员
     */
    private String saleMan;
    /**
     * 财务员
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
