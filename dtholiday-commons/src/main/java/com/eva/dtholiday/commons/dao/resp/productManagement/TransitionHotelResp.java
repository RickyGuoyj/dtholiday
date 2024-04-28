package com.eva.dtholiday.commons.dao.resp.productManagement;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/27 20:47
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class TransitionHotelResp {

    /**
     * 过渡酒店ID
     */
    private Integer transitionHotelId;

    /**
     * 过渡酒店名称
     */
    private String transitionHotelName;

    /**
     * 过渡酒店房间类型
     */
    private String transitionHotelType;

    /**
     * 生效日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date effectiveDate;

    /**
     * 失效日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date expiryDate;

    /**
     * 总量
     */
    private int totalNum;

    /**
     * 余量
     */
    private int remainNum;

    /**
     * 备注
     */
    private String remark;

}
