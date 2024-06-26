package com.eva.dtholiday.system.service.portalmanagement;

import java.util.List;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandRecommendation;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandRecommendationReq;
import com.eva.dtholiday.commons.dao.resp.portalmanagement.IslandRecommendationQueryListResp;

public interface IslandRecommendationService {
    public ResponseApi islandRecommendationAdd(IslandRecommendationReq islandRecommendationReq);

    public ResponseApi islandRecommendationDelete(List<Integer> recommendationIndexCodeList);

    public ResponseApi islandRecommendationUpdate(IslandRecommendationReq islandRecommendationReq);

    public ResponseApi<IslandRecommendation> islandRecommendationQueryDetail(int recommendationIndexCode);

    public ResponseApi<IslandRecommendationQueryListResp> islandRecommendationQueryList();
}
