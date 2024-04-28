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
 * @create-time 2024/4/27 21:33
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dt_plane_ticket")
public class PlaneTicket extends Model<PlaneTicket> {

    /**
     * 机票ID
     */
    @TableId(value = "plane_ticket_id", type = IdType.AUTO)
    private Integer planeTicketId;

    /**
     * 航空公司名称
     */
    private String airlineCompanyName;

    /**
     * 出发日期
     */
    private Date departureDate;

    /**
     * 返回日期
     */
    private Date returnDate;

    /**
     * 旅行天数描述
     */
    private String days;

    /**
     * 去程航班号
     */
    private String departureFlight;

    /**
     * 返程航班号
     */
    private String returnFlight;

    /**
     * 出发地点
     */
    private String departurePlace;

    /**
     * 到达地点
     */
    private String arrivalPlace;

    /**
     * 总量
     */
    private int totalNum;

    /**
     * 余量
     */
    private int remainNum;

    /**
     * 价格
     */
    private double price;

    /**
     * 货币类型
     */
    private int currencyType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public static final String AIRLINE_COMPANY_NAME = "airline_company_name";

    public static final String DEPARTURE_DATE = "departure_date";

    public static final String RETURN_DATE = "return_date";

    public static final String DEPARTURE_FLIGHT = "departure_flight";

    public static final String RETURN_FLIGHT = "return_flight";

    public static final String DEPARTURE_PLACE = "departure_place";

    public static final String ARRIVAL_PLACE = "arrival_place";

}
