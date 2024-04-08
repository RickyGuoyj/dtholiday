package com.eva.dtholiday.commons.dao.resp.portalmanagement;

import java.util.List;

import com.eva.dtholiday.commons.dao.dto.IslandManagementTagInfo;

import lombok.Data;

/**
 * @author fengsuohua
 */
@Data
public class IslandManagementQueryListResp {
    /**
     * 当前页
     */
    private int page;
    /**
     * 每页大小
     */
    private int pageSize;

    private List<IslandManagementTagInfo> islandManagementTagInfoList;
}
