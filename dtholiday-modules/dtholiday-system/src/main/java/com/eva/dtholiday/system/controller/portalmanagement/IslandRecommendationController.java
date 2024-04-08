package com.eva.dtholiday.system.controller.portalmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandRecommendation;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandRecommendationReq;
import com.eva.dtholiday.commons.dao.resp.portalmanagement.IslandRecommendationQueryListResp;
import com.eva.dtholiday.system.service.portalmanagement.IslandRecommendationService;

@RestController
@RequestMapping("/erp/portalManagement/islandRecommendation")
public class IslandRecommendationController {
    @Autowired
    private IslandRecommendationService islandRecommendationService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public ResponseApi islandRecommendationAdd(@RequestBody IslandRecommendationReq req) {
        return islandRecommendationService.islandRecommendationAdd(req);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public ResponseApi islandRecommendationDelete(@RequestBody IslandRecommendationReq req) {
        return islandRecommendationService.islandRecommendationDelete(req.getRecommendationIndexCode());
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    public ResponseApi islandRecommendationUpdate(@RequestBody IslandRecommendationReq req) {
        return islandRecommendationService.islandRecommendationUpdate(req);
    }

    /**
     * 查询列表
     */
    @PostMapping("/querylist")
    public ResponseApi<IslandRecommendationQueryListResp>
        islandRecommendationQueryList(@RequestBody IslandRecommendationReq req) {
        return islandRecommendationService.islandRecommendationQueryList(req);
    }

    /**
     * 查询详情
     */
    @PostMapping("/querydetail")
    public ResponseApi<IslandRecommendation> islandRecommendationQueryDetail(@RequestBody IslandRecommendationReq req) {
        return islandRecommendationService.islandRecommendationQueryDetail(req.getRecommendationIndexCode());
    }
}
