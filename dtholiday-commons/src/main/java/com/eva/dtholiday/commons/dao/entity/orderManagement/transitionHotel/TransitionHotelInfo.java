package com.eva.dtholiday.commons.dao.entity.orderManagement.transitionHotel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/17 2:22
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class TransitionHotelInfo {

    private int transitionHotelId;

    private String transitionHotelName;

    private String transitionHotelType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date effectiveDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date expiryDate;
}
