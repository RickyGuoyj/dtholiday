package com.eva.dtholiday.commons.dao.req.portalmanagement;

import com.eva.dtholiday.commons.dao.req.BasePage;
import lombok.Data;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/13 7:30
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/

@Data
public class IslandQuotationQueryListReq extends BasePage {

    private Integer islandIndexCode;

    private String quotationName;


}
