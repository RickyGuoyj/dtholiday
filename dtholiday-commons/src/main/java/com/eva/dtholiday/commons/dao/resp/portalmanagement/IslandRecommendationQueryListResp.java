package com.eva.dtholiday.commons.dao.resp.portalmanagement;

import java.util.List;

import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandRecommendation;

import lombok.Data;

@Data
public class IslandRecommendationQueryListResp {
    private List<IslandRecommendation> islandRecommendationList;
}
