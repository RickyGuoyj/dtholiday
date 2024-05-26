package com.eva.dtholiday.commons.dao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/26 10:35
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class IslandHotelQueryInfo {

    private Integer islandHotelId;

    private String hotelRoomType;

    private Integer trafficType;

    private String trafficName;

    private Integer mealType;

    private String mealName;

    private String delayHotelRoomType;

    private Integer remainingDays;

    private Integer islandIndexCode;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date effectiveDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date expiryDate;
}
