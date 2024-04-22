package com.eva.dtholiday.commons.dao.req.systemmanagement;

import java.util.List;

import lombok.Data;

@Data
public class TrafficDeleteReq {
    private List<Integer> trafficIndexCodeList;
}
