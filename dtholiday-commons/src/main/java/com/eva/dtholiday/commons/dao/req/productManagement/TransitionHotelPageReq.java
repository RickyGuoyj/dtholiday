package com.eva.dtholiday.commons.dao.req.productManagement;

import com.eva.dtholiday.commons.dao.req.BasePage;
import lombok.Data;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/27 20:40
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class TransitionHotelPageReq extends BasePage {
    /**
     * 过渡酒店名称，用于模糊查询
     */
    private String transitionHotelName;

    /**
     * 过渡酒店房间类型，用于模糊查询
     */
    private String transitionHotelType;

    private Date effectiveDate;

    private Date expiryDate;


}
