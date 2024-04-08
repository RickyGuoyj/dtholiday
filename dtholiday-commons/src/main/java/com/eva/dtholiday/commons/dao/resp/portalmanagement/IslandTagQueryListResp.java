package com.eva.dtholiday.commons.dao.resp.portalmanagement;

import java.util.List;

import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandTag;

import lombok.Data;

@Data
public class IslandTagQueryListResp {
    /**
     * 当前页数
     */
    private int page;
    /**
     * 每页条数
     */
    private int pageSize;
    /**
     * 数据
     */
    private List<IslandTag> islandTagList;
}
