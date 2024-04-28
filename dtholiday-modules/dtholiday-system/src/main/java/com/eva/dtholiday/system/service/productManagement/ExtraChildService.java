package com.eva.dtholiday.system.service.productManagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.req.productManagement.ExtraChildExpensePageReq;
import com.eva.dtholiday.commons.dao.req.productManagement.ExtraChildExpenseQueryReq;
import com.eva.dtholiday.commons.dao.req.productManagement.ExtraChildExpenseReq;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/27 18:05
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
public interface ExtraChildService {
    ResponseApi update(ExtraChildExpenseReq req);

    ResponseApi add(ExtraChildExpenseReq req);

    ResponseApi delete(ExtraChildExpenseQueryReq req);

    ResponseApi queryList(ExtraChildExpensePageReq req);

    ResponseApi queryDetail(ExtraChildExpenseQueryReq req);
}
