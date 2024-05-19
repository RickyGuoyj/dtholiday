package com.eva.dtholiday.commons.dao.entity.orderManagement.islandhotelorder;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.sql.Date;

@Data
public class IslandHotelInfo {
    private Integer islandHotelId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date effectiveDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date expiryDate;
}
