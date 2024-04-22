package com.eva.dtholiday.system.controller.systemmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.systemmanagement.TrafficDeleteReq;
import com.eva.dtholiday.commons.dao.req.systemmanagement.TrafficReq;
import com.eva.dtholiday.system.service.systemmanagement.TrafficManagementService;

@RestController
@RequestMapping("/erp/systemManagement/trafficManagement")
public class TrafficManagementController {

    @Autowired
    private TrafficManagementService trafficManagementService;

    @PostMapping("/add")
    public ResponseApi addTraffic(@RequestBody TrafficReq req) {
        return trafficManagementService.addTraffic(req);
    }

    @PostMapping("/update")
    public ResponseApi updateTraffic(@RequestBody TrafficReq req) {
        return trafficManagementService.updateTraffic(req);
    }

    @PostMapping("/delete")
    public ResponseApi deleteTraffic(@RequestBody TrafficDeleteReq req) {
        return trafficManagementService.deleteTraffic(req);
    }

    @PostMapping("/querylist")
    public ResponseApi queryTrafficlist() {
        return trafficManagementService.queryTrafficlist();
    }

    @PostMapping("/querydetail")
    public ResponseApi queryTrafficDetail(@RequestBody TrafficReq req) {
        return trafficManagementService.queryTrafficDetail(req);
    }
}
