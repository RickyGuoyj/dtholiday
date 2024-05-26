package com.eva.dtholiday.commons.dao.req.productManagement;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class IslandHotelQueryAllReq {
    /**
     * 岛屿主键id
     */
    @NotNull
    private Integer islandIndexCode;

    /**
     * 开始日期，精确查询
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull
    private Date effectiveDate;

    /**
     * 结束日期，精确查询
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull
    private Date expiryDate;
}
