package com.eva.dtholiday.system.controller.portal;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.resp.portal.RecommendIslandResp;
import com.eva.dtholiday.system.service.portal.PortalService;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/20 10:00
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@RestController
@RequestMapping("/portal/island")
public class PortalController {
    private PortalService portalService;

    /**
     * 获取岛屿推荐
     *
     * @return
     */
    @GetMapping("/get_recommend_island")
    public ResponseApi<List<RecommendIslandResp>> getRecommendIsland() {
        return portalService.getRecommendIsland();
    }

    @GetMapping("/get_tags")
    public ResponseApi getTags() {
        return portalService.getTags();
    }
}
