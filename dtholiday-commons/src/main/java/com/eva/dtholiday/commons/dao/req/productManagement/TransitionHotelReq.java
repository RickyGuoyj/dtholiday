package com.eva.dtholiday.commons.dao.req.productManagement;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/27 20:27
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class TransitionHotelReq {

    @ExcelProperty(value = "编号")
    private Integer transitionHotelId;

    /**
     * 过渡酒店名称
     */
    @ExcelProperty(value = "酒店名称")
    private String transitionHotelName;
    /**
     * 过渡酒店房间类型
     */
    @ExcelProperty(value = "房型")
    private String transitionHotelType;
    /**
     * 生效日期
     */
    @ExcelProperty(value = "生效日期")
    @DateTimeFormat("yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date effectiveDate;
    /**
     * 失效日期
     */
    @ExcelProperty(value = "失效日期")
    @DateTimeFormat("yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date expiryDate;
    /**
     * 总量
     */
    @ExcelProperty(value = "总房间数")
    private Integer totalNum;
    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;
}
