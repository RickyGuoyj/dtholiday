package com.eva.dtholiday.system.controller.portalmanagement;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandRecommendation;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandRecommendationReq;

@RestController
@RequestMapping("/erp/portalManagement/islandRecommendation")
public class IslandRecommendationController {
    /**
     * 新增
     */
    @RequestMapping("/add")
    public ResponseApi islandRecommendationAdd(@RequestBody IslandRecommendationReq req) {
        ResponseApi<Object> response = ResponseApi.ok();
        return response;
    }
    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseApi islandRecommendationDelete(@RequestBody String recommendationIndexCode) {
        ResponseApi<Object> response = ResponseApi.ok();
        return response;
    }
    /**
     * 更新
     */
    @RequestMapping("/update")
    public ResponseApi islandRecommendationUpdate(@RequestBody IslandRecommendationReq req) {
        ResponseApi<Object> response = ResponseApi.ok();
        return response;
    }
    /**
     * 查询列表
     */
    @RequestMapping("/querylist")
    public ResponseApi<List<IslandRecommendation>> islandRecommendationQueryList() {
        ResponseApi<List<IslandRecommendation>> response = ResponseApi.ok();
        return response;
    }
    /**
     * 查询详情
     */
    @RequestMapping("/querydetail")
    public ResponseApi<IslandRecommendation> islandRecommendationQueryDetail(@RequestBody String recommendationIndexCode) {
        ResponseApi<IslandRecommendation> response = ResponseApi.ok();
        return response;
    }
}
