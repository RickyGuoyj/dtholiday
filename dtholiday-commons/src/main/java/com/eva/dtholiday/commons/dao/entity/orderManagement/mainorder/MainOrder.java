package com.eva.dtholiday.commons.dao.entity.orderManagement.mainorder;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dt_order_main")
public class MainOrder extends Model<MainOrder> {
    private static final long serialVersionUID = 1L;

    @TableId(value = "main_order_id", type = IdType.AUTO)
    private Integer mainOrderId;
    private Integer islandHotelOrderId;
    private Integer islandOrderStatus;
    private Integer planeTicketOrderId;
    private Integer planeTicketOrderStatus;
    private Integer transitionHotelOrderId;
    private Integer transitionHotelOrderStatus;
    private String totalPrice;
    private Integer orderStatus;
    private Integer financialStatus;
    private String orderCreator;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String saleMan;
    private Timestamp paymentTime;
    /**
     * 备注
     */
    private String remarks;

}
