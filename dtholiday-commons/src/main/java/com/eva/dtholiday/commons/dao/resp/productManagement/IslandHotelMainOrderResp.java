package com.eva.dtholiday.commons.dao.resp.productManagement;

import lombok.Data;

@Data
public class IslandHotelMainOrderResp {
    /**
     * 酒店主键id
     */
    private Integer islandHotelId;
    /**
     * 岛屿主键
     */
    private Integer islandIndexCode;
    /**
     * 岛屿中文名称
     */
    private String islandCnName;
    /**
     * 酒店房间类型
     */
    private String hotelRomType ;
    /**
     * 延迟酒店房间类型
     */
    private String delayHotelRoomType;
    /**
     * 上岛交通工具主键
     */
    private Integer trafficIndexCode;
    /**
     * 交通工具名称
     */
    private String trafficName;
    /**
     * 餐食主键
     */
    private Integer mealIndexCode;
    /**
     * 餐食名称
     */
    private String mealName;
    /**
     * 餐食描述
     */
    private String mealDesc;

}
