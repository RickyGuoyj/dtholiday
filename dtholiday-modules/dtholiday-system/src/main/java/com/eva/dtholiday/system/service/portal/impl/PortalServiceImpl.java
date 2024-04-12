package com.eva.dtholiday.system.service.portal.impl;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandRecommendation;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandTag;
import com.eva.dtholiday.commons.dao.mapper.portalmanagement.IslandRecommendationMapper;
import com.eva.dtholiday.commons.dao.mapper.portalmanagement.IslandTagMapper;
import com.eva.dtholiday.commons.dao.resp.portal.RecommendIslandResp;
import com.eva.dtholiday.commons.dao.resp.portal.TagResp;
import com.eva.dtholiday.system.service.portal.PortalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PortalServiceImpl implements PortalService {
    @Resource
    private IslandRecommendationMapper islandRecommendationMapper;

    @Resource
    private IslandTagMapper islandTagMapper;

    @Override
    public ResponseApi<List<RecommendIslandResp>> getRecommendIsland() {
        List<IslandRecommendation> islandRecommendations = islandRecommendationMapper.selectList(null);
        List<RecommendIslandResp> recommendIslandRespList = new ArrayList<>();
        if (islandRecommendations != null && islandRecommendations.size() > 0) {
            for (IslandRecommendation islandRecommendation : islandRecommendations) {
                RecommendIslandResp recommendIslandResp = new RecommendIslandResp();
                recommendIslandResp.setIsland_desc(islandRecommendation.getIslandCnName());
                recommendIslandResp.setIsland_id(islandRecommendation.getIslandIndexCode());
                recommendIslandResp.setIsland_image(islandRecommendation.getRecommendationImage());
                recommendIslandResp.setIsland_name(islandRecommendation.getIslandCnName());
                recommendIslandRespList.add(recommendIslandResp);
            }
        }
        return ResponseApi.ok(recommendIslandRespList);
    }

    @Override
    public ResponseApi<List<TagResp>> getTags() {
        List<IslandTag> islandTags = islandTagMapper.selectList(null);
        List<TagResp> tagRespList = new ArrayList<>();
        if (islandTags != null && islandTags.size() > 0) {
            for (IslandTag islandTag : islandTags) {
                TagResp tagResp = new TagResp();
                tagResp.setTag_image(islandTag.getTagImage());
                tagResp.setTag_name(islandTag.getTagName());
                tagRespList.add(tagResp);
            }
        }
        return ResponseApi.ok(tagRespList);
    }

}
