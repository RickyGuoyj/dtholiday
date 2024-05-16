package com.eva.dtholiday.commons.dao.entity.productManagement;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/27 20:29
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dt_transition_hotel")
public class TransitionHotel extends Model<TransitionHotel> {

    /**
     * 过渡酒店ID
     */
    @TableId(value = "transition_hotel_id", type = IdType.AUTO)
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
    private Date effectiveDate;

    /**
     * 失效日期
     */
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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public static final String TRANSITION_HOTEL_ID = "transition_hotel_id";

    public static final String TRANSITION_HOTEL_NAME = "transition_hotel_name";

    public static final String TRANSITION_HOTEL_TYPE = "transition_hotel_type";

    public static final String EFFECTIVE_DATE = "effective_date";

    public static final String EXPIRY_DATE = "expiry_date";
}
