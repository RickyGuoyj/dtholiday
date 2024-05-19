package com.eva.dtholiday.commons.dao.req.productManagement;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class IslandHotelCalculateReq {
    private Integer islandHotelId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date effectiveDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date expiryDate;

    private Integer adultNum;
    private Integer childNum;

    private Integer firstChildAge;
    private Integer secondChildAge;

    public static final String ISLAND_HOTEL_ID = "island_hotel_id";

}
