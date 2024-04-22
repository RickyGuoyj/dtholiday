package com.eva.dtholiday.system.service.systemmanagement.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.systemmanagement.Traffic;
import com.eva.dtholiday.commons.dao.mapper.systemmanagement.TrafficMapper;
import com.eva.dtholiday.commons.dao.req.systemmanagement.TrafficDeleteReq;
import com.eva.dtholiday.commons.dao.req.systemmanagement.TrafficReq;
import com.eva.dtholiday.commons.dao.resp.systemmanagement.TrafficManagementQueryListResp;
import com.eva.dtholiday.system.service.systemmanagement.TrafficManagementService;

@Service
public class TrafficManagementServiceImpl implements TrafficManagementService {
    @Resource
    private TrafficMapper trafficMapper;

    @Override
    public ResponseApi<Integer> addTraffic(TrafficReq req) {
        Traffic traffic = new Traffic();
        traffic.setTrafficName(req.getTrafficName());
        int insert = trafficMapper.insert(traffic);
        return ResponseApi.ok(insert);
    }

    @Override
    public ResponseApi<Integer> updateTraffic(TrafficReq req) {
        Traffic traffic = new Traffic();
        traffic.setTrafficName(req.getTrafficName());
        traffic.setTrafficIndexCode(req.getTrafficIndexCode());
        int update = trafficMapper.updateById(traffic);
        return ResponseApi.ok(update);
    }

    @Override
    public ResponseApi<Integer> deleteTraffic(TrafficDeleteReq req) {
        int i = trafficMapper.deleteBatchIds(req.getTrafficIndexCodeList());
        return ResponseApi.ok(i);
    }

    @Override
    public ResponseApi<TrafficManagementQueryListResp> queryTrafficlist() {
        List<Traffic> records = trafficMapper.selectList(null);
        TrafficManagementQueryListResp resp = new TrafficManagementQueryListResp();
        resp.setTrafficList(records);
        return ResponseApi.ok(resp);
    }

    @Override
    public ResponseApi<Traffic> queryTrafficDetail(TrafficReq req) {
        Traffic traffic = trafficMapper.selectById(req.getTrafficIndexCode());
        return ResponseApi.ok(traffic);
    }
}
