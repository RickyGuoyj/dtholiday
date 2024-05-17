package com.eva.dtholiday.commons.dao.entity.orderManagement;

import lombok.Data;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/17 2:22
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class CustomerInfo {
    private String customerName;
    private int adultNum;
    private int childNum;
    private int roomNum;
    private int nights;
    private int firstChildAge;
    private int secondChildAge;
}
