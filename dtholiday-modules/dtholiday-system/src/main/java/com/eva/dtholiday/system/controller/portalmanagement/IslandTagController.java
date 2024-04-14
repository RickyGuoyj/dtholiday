package com.eva.dtholiday.system.controller.portalmanagement;

import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandTagDeleteReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandTag;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandTagReq;
import com.eva.dtholiday.commons.dao.resp.portalmanagement.IslandTagQueryListResp;
import com.eva.dtholiday.system.service.portalmanagement.IslandTagService;

@RestController
@RequestMapping("/erp/portalManagement/islandTag")
public class IslandTagController {

    @Autowired
    private IslandTagService islandTagService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public ResponseApi islandTagAdd(@RequestBody IslandTagReq req) {
        return islandTagService.islandTagAdd(req);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public ResponseApi islandTagDelete(@RequestBody IslandTagDeleteReq req) {
        return islandTagService.islandTagDelete(req.getTagIndexCodeList());
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    public ResponseApi islandTagUpdate(@RequestBody IslandTagReq req) {
        return islandTagService.islandTagUpdate(req);
    }

    /**
     * 查询列表
     */
    @PostMapping("/querylist")
    public ResponseApi<IslandTagQueryListResp> islandTagQueryList(@RequestBody IslandTagReq islandTagReq) {
        return islandTagService.islandTagQueryList(islandTagReq);
    }

    /**
     * 查询详情
     */
    @PostMapping("/querydetail")
    public ResponseApi<IslandTag> islandTagQueryDetail(@RequestBody IslandTagReq islandTagReq) {
        return islandTagService.islandTagQueryDetail(islandTagReq.getTagIndexCode());
    }
}
