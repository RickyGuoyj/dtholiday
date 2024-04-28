package com.eva.dtholiday.commons.dao.req.productManagement;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/27 21:32
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class PlaneTicketReq {

    /**
     * 机票ID
     */
    private Integer planeTicketId;

    /**
     * 航空公司名称
     */
    private String airlineCompanyName;

    /**
     * 出发日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date departureDate;

    /**
     * 返回日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date returnDate;

    /**
     * 旅行天数描述
     */
    private String days;

    /**
     * 去程航班号
     */
    private String departureFlight;

    /**
     * 返程航班号
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
     * 总量
     */
    private Integer totalNum;

    /**
     * 价格
     */
    private double price;

    /**
     * 货币类型（例如：1-人民币，2-美元等）
     */
    private int currencyType;

}
