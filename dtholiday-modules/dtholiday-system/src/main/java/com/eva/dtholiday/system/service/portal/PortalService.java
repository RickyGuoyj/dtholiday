package com.eva.dtholiday.system.service.portal;

import java.util.List;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.resp.portal.RecommendIslandResp;
import com.eva.dtholiday.commons.dao.resp.portal.TagResp;

public interface PortalService {
    /**
     * 前台获取推荐岛屿
     *
     * @return
     */
    ResponseApi<List<RecommendIslandResp>> getRecommendIsland();

    /**
     * 前台获取标签
     *
     * @return
     */
    ResponseApi<List<TagResp>> getTags();
}
