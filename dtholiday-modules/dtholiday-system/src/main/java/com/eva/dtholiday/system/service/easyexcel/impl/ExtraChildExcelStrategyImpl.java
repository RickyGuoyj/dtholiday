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
import com.eva.dtholiday.commons.dao.entity.productManagement.ExtraChildExpense;
import com.eva.dtholiday.commons.dao.mapper.productManagement.ExtraChildExpenseMapper;
import com.eva.dtholiday.commons.dao.req.productManagement.ExtraChildExpenseReq;
import com.eva.dtholiday.system.service.easyexcel.EasyExcelStrategy;
import com.eva.dtholiday.system.service.easyexcel.EasyExcelStrategyFactory;
import com.eva.dtholiday.system.service.productManagement.ExtraChildService;

@Service
public class ExtraChildExcelStrategyImpl implements EasyExcelStrategy {
    @Autowired
    private ExtraChildExpenseMapper extraChildExpenseMapper;

    @Autowired
    private ExtraChildService extraChildService;

    @PostConstruct
    public void register() {
        EasyExcelStrategyFactory.register(2, this);
    }

    @Override
    public void exportExcel(HttpServletResponse response) {
        try {
            String fileName = "额外儿童价格";
            setExcelResponseProp(response, fileName);
            List<ExtraChildExpenseReq> extraChildExpenseReqList = new ArrayList<>();
            List<ExtraChildExpense> extraChildExpenses = extraChildExpenseMapper.selectList(null);
            extraChildExpenses.stream().forEach(extraChildExpense -> {
                ExtraChildExpenseReq extraChildExpenseReq = new ExtraChildExpenseReq();
                BeanUtils.copyProperties(extraChildExpense, extraChildExpenseReq);
                extraChildExpenseReqList.add(extraChildExpenseReq);
            });
            EasyExcel.write(response.getOutputStream()).head(ExtraChildExpenseReq.class).autoCloseStream(Boolean.FALSE)
                .sheet(fileName).doWrite(extraChildExpenseReqList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseApi importExcel(MultipartFile file) {
        try {
            List<ExtraChildExpenseReq> extraChildExpenseReqList =
                EasyExcel.read(file.getInputStream()).head(ExtraChildExpenseReq.class).sheet().doReadSync();
            extraChildExpenseReqList.stream().forEach(extraChildExpenseReq -> {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("extra_child_expense_id", extraChildExpenseReq.getExtraChildExpenseId());
                Long l = extraChildExpenseMapper.selectCount(queryWrapper);
                if (l > 0) {
                    // 更新
                    extraChildService.update(extraChildExpenseReq);
                } else {
                    // 新增
                    extraChildService.add(extraChildExpenseReq);
                }
            });
            return ResponseApi.ok(extraChildExpenseReqList.size());
        } catch (IOException e) {
            return ResponseApi.error("导入失败");
        }
    }
}
