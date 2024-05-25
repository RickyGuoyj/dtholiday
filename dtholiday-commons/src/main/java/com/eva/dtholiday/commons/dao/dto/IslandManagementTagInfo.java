package com.eva.dtholiday.commons.dao.dto;

import lombok.Data;

import java.util.List;

@Data
public class IslandManagementTagInfo {
    private int islandIndexCode;
    private String islandEnName;
    private String islandCnName;
    private String islandDesc;
    private String islandFile;
    private List<String> tagName;
    private String tagIndexCode;
}
