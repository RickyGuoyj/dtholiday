package com.eva.dtholiday.commons.dao.req.productManagement;

import com.eva.dtholiday.commons.dao.req.BasePage;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/27 21:46
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class PlaneTicketPageReq extends BasePage {
    /**
     * 航空公司名称，支持模糊查询
     */
    private String airlineCompanyName;

    /**
     * 出发日期，精确查询
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date departureDate;

    /**
     * 返回日期，精确查询
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date returnDate;

    /**
     * 去程航班号，精准查询
     */
    private String departureFlight;

    /**
     * 返程航班号，精准查询
     */
    private String returnFlight;

    /**
     * 出发地点，支持模糊查询
     */
    private String departurePlace;

    /**
     * 到达地点，支持模糊查询
     */
    private String arrivalPlace;



}
