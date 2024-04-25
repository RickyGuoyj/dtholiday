package com.eva.dtholiday.system.controller.portalmanagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandArticle;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandManagement;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandArticleQueryDto;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandArticleReq;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandManagementReq;
import com.eva.dtholiday.system.service.portalmanagement.IslandArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/14 4:21
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@RestController
@RequestMapping("/erp/portalManagement/islandArticleManagement")
public class IslandArticleController {

    @Autowired
    private IslandArticleService islandArticleService;

    @PostMapping("/add")
    public ResponseApi islandManagementAdd(@RequestBody IslandArticleReq req) {
        return islandArticleService.islandArticleAdd(req);
    }

    @PostMapping("/delete")
    public ResponseApi islandManagementDelete(@RequestBody IslandArticleQueryDto req) {
        return islandArticleService.islandArticleDelete(req);
    }

    @PostMapping("/update")
    public ResponseApi islandManagementUpdate(@RequestBody IslandArticleReq req) {
        return islandArticleService.islandArticleUpdate(req);
    }

    @PostMapping("/querylist")
    public ResponseApi islandManagementQueryList(@RequestBody  IslandArticleQueryDto req) {
        return islandArticleService.islandArticleQueryList(req);
    }

    @PostMapping("/querydetail")
    public ResponseApi islandManagementQueryDetail(@RequestBody IslandArticleReq req) {
        return islandArticleService.islandArticleQueryDetail(req.getArticleIndexCode());
    }

}
