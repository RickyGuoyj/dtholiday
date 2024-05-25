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
import com.eva.dtholiday.commons.dao.entity.productManagement.TransitionHotel;
import com.eva.dtholiday.commons.dao.mapper.productManagement.TransitionHotelMapper;
import com.eva.dtholiday.commons.dao.req.productManagement.TransitionHotelReq;
import com.eva.dtholiday.system.service.easyexcel.EasyExcelStrategy;
import com.eva.dtholiday.system.service.easyexcel.EasyExcelStrategyFactory;
import com.eva.dtholiday.system.service.productManagement.TransitionHotelService;

@Service
public class TransitionHotelExcelStrategyImpl implements EasyExcelStrategy {

    @Autowired
    private TransitionHotelMapper transitionHotelMapper;

    @Autowired
    private TransitionHotelService transitionHotelService;

    @PostConstruct
    public void register() {
        EasyExcelStrategyFactory.register(4, this);
    }

    @Override
    public void exportExcel(HttpServletResponse response) {
        try {
            String fileName = "过度酒店价格";
            setExcelResponseProp(response, fileName);
            List<TransitionHotelReq> transitionHotelReqList = new ArrayList<>();
            List<TransitionHotel> transitionHotels = transitionHotelMapper.selectList(null);
            transitionHotels.stream().forEach(transitionHotel -> {
                TransitionHotelReq transitionHotelReq = new TransitionHotelReq();
                BeanUtils.copyProperties(transitionHotel, transitionHotelReq);
                transitionHotelReqList.add(transitionHotelReq);
            });
            EasyExcel.write(response.getOutputStream()).head(TransitionHotelReq.class).autoCloseStream(Boolean.FALSE)
                .sheet(fileName).doWrite(transitionHotelReqList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseApi importExcel(MultipartFile file) {
        try {
            List<TransitionHotelReq> transitionHotelReqList =
                EasyExcel.read(file.getInputStream()).head(TransitionHotelReq.class).sheet().doReadSync();
            transitionHotelReqList.stream().forEach(transitionHotelReq -> {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("transition_hotel_id", transitionHotelReq.getTransitionHotelId());
                Long l = transitionHotelMapper.selectCount(queryWrapper);
                if (l > 0) {
                    // 更新
                    transitionHotelService.update(transitionHotelReq);
                } else {
                    // 新增
                    transitionHotelService.add(transitionHotelReq);
                }
            });
            return ResponseApi.ok(transitionHotelReqList.size());
        } catch (IOException e) {
            return ResponseApi.error("导入失败");
        }
    }
}
