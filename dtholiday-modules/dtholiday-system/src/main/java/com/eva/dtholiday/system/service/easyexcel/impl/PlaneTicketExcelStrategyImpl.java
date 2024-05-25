package com.eva.dtholiday.system.service.easyexcel.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.productManagement.PlaneTicket;
import com.eva.dtholiday.commons.dao.mapper.productManagement.PlaneTicketMapper;
import com.eva.dtholiday.commons.dao.req.productManagement.PlaneTicketReq;
import com.eva.dtholiday.system.service.easyexcel.EasyExcelStrategy;
import com.eva.dtholiday.system.service.easyexcel.EasyExcelStrategyFactory;
import com.eva.dtholiday.system.service.productManagement.PlaneTicketService;

@Service
public class PlaneTicketExcelStrategyImpl implements EasyExcelStrategy {

    @Autowired
    private PlaneTicketMapper planeTicketMapper;

    @Autowired
    private PlaneTicketService planeTicketService;

    @PostConstruct
    public void register() {
        EasyExcelStrategyFactory.register(3, this);
    }

    @Override
    public void exportExcel(HttpServletResponse response) {
        try {
            String fileName = "机票产品价格";
            setExcelResponseProp(response, fileName);
            List<PlaneTicketReq> planeTicketReqList = new ArrayList<>();
            List<PlaneTicket> planeTickets = planeTicketMapper.selectList(null);
            planeTickets.stream().forEach(planeTicket -> {
                PlaneTicketReq planeTicketReq = new PlaneTicketReq();
                BeanUtils.copyProperties(planeTicket, planeTicketReq);
                planeTicketReqList.add(planeTicketReq);
            });
            EasyExcel.write(response.getOutputStream()).head(PlaneTicketReq.class).autoCloseStream(Boolean.FALSE)
                .sheet(fileName).doWrite(planeTicketReqList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseApi importExcel(MultipartFile file) {
        try {
            List<PlaneTicketReq> planeTicketReqList =
                EasyExcel.read(file.getInputStream()).head(PlaneTicketReq.class).sheet().doReadSync();
            planeTicketReqList.stream().forEach(planeTicketReq -> {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("plane_ticket_id", planeTicketReq.getPlaneTicketId());
                Long l = planeTicketMapper.selectCount(queryWrapper);
                if (l > 0) {
                    // 更新
                    planeTicketService.update(planeTicketReq);
                } else {
                    // 新增
                    planeTicketService.add(planeTicketReq);
                }
            });
            return ResponseApi.ok(planeTicketReqList.size());
        } catch (IOException e) {
            return ResponseApi.error("导入失败");
        }
    }
}
