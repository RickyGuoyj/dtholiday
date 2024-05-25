package com.eva.dtholiday.system.controller.financialManagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.financialManagement.PaymentCheckReq;
import com.eva.dtholiday.commons.dao.req.financialManagement.PaymentPageReq;
import com.eva.dtholiday.system.service.financialManagement.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/19 15:05
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@RestController
@RequestMapping("/erp/paymentManagement")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/queryList")
    public ResponseApi queryPaymentList(@RequestBody PaymentPageReq req) {
        return paymentService.queryPaymentList(req);
    }

    @PostMapping("/updateFinancialStatus")
    public ResponseApi updateFinancialStatusByFinancialMan(@RequestBody PaymentCheckReq req) {
        return paymentService.updateFinancialStatusByFinancialMan(req);
    }
}
