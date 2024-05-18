package com.eva.dtholiday.commons.dao.entity.orderManagement.planeTicket;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/17 1:13
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class PlaneTicketInfo {
    private int planeTicketId;
    private String airlineCompanyName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date departureDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date returnDate;
    private String days;
    private String departureFlight;
    private String returnFlight;
    private String departurePlace;
    private String arrivalPlace;
    private String ticketNumber;
    private double price;
    private int currencyType;
}
