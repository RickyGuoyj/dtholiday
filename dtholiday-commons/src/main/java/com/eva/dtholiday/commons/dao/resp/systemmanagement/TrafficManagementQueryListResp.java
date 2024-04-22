package com.eva.dtholiday.commons.dao.resp.systemmanagement;

import java.util.List;

import com.eva.dtholiday.commons.dao.entity.systemmanagement.Traffic;

import lombok.Data;

@Data
public class TrafficManagementQueryListResp {
    private List<Traffic> trafficList;
}
