package com.eva.dtholiday.system.controller.portalmanagement;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandTag;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandTagReq;

@RestController
@RequestMapping("/erp/portalManagement/islandTag")
public class IslandTagController {
    /**
     * 新增
     */
    @RequestMapping("/add")
    public ResponseApi islandTagAdd(@RequestBody IslandTagReq req) {
        ResponseApi<Object> response = ResponseApi.ok();
        return response;
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseApi islandTagDelete(@RequestBody String tagIndexCode) {
        ResponseApi<Object> response = ResponseApi.ok();
        return response;
    }

    /**
     * 更新
     */
    @RequestMapping("/update")
    public ResponseApi islandTagUpdate(@RequestBody IslandTagReq req) {
        ResponseApi<Object> response = ResponseApi.ok();
        return response;
    }

    /**
     * 查询列表
     */
    @RequestMapping("/querylist")
    public ResponseApi<List<IslandTag>> islandTagQueryList() {
        ResponseApi<List<IslandTag>> response = ResponseApi.ok();
        return response;
    }

    /**
     * 查询详情
     */
    @RequestMapping("/querydetail")
    public ResponseApi<IslandTag> islandTagQueryDetail(@RequestBody String tagIndexCode) {
        ResponseApi<IslandTag> response = ResponseApi.ok();
        return response;
    }
}
