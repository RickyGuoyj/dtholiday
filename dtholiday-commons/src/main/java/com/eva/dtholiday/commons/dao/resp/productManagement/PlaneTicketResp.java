package com.eva.dtholiday.commons.dao.resp.productManagement;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/27 22:09
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class PlaneTicketResp {


    /**
     * 机票ID
     */
    private int planeTicketId;

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
    private int totalNum;

    /**
     * 余量
     */
    private int remainNum;

    /**
     * 价格
     */
    private double price;

    /**
     * 货币类型
     */
    private int currencyType;

}
