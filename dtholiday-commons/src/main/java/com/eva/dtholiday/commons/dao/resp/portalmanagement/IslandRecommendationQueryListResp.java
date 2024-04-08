package com.eva.dtholiday.commons.dao.resp.portalmanagement;

import java.util.List;

import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandRecommendation;

import lombok.Data;

@Data
public class IslandRecommendationQueryListResp {
    private int page;
    private int pageSize;
    private List<IslandRecommendation> islandRecommendationList;
}
