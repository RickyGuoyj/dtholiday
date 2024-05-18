package com.eva.dtholiday.commons.dao.req.orderManagement;

import com.eva.dtholiday.commons.dao.req.BasePage;
import lombok.Data;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/18 0:20
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class PlaneTicketOrderPageReq extends BasePage {

    private Integer planeTicketOrderId;

    private String customerName;

    private Date departureDate;

    private Date returnDate;

    private String airlineCompanyName;

    private Integer orderStatus;

    private Integer financialStatus;

    private String saleMan;
}
