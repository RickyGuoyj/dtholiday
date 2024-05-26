package com.eva.dtholiday.commons.dao.dto;

import lombok.Data;

@Data
public class IslandManagementInfo {
    /**
     * 岛屿主键
     */
    private Integer islandIndexCode;
    /**
     * 岛屿中文名
     */
    private String islandCnName;
}
