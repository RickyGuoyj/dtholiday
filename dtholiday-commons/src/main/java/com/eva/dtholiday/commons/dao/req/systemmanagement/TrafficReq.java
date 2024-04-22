package com.eva.dtholiday.commons.dao.req.systemmanagement;

import lombok.Data;

/**
 * 新增、修改、查询详情交通工具请求
 */
@Data
public class TrafficReq {
    private Integer trafficIndexCode;
    private String trafficName;
}
