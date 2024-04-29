package com.eva.dtholiday.commons.dao.req.productManagement;

import com.eva.dtholiday.commons.dao.req.BasePage;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/29 0:53
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class IslandHotelPageReq extends BasePage {

    /**
     * 岛屿编码
     */
    private Integer islandIndexCode;

    /**
     * 上岛工具类型
     */
    private Integer trafficType;

    /**
     * 餐型类型
     */
    private Integer mealType;

    /**
     * 房型描述
     */
    private String hotelRoomType;

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
}
