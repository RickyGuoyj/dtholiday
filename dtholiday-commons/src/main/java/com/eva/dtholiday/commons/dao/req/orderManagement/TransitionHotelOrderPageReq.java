package com.eva.dtholiday.commons.dao.req.orderManagement;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/18 0:27
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class TransitionHotelOrderPageReq {
    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 中转酒店名称
     */
    private String transitionHotelName;
    /**
     * 生效日期
     */
    private Date effectiveDate;
    /**
     * 失效日期
     */
    private Date expiryDate;
    /**
     * 订单状态
     */
    private Integer orderStatus;
    /**
     * 财务状态
     */
    private Integer financialStatus;
    /**
     * 销售员
     */
    private String saleMan;
}
