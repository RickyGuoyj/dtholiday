package com.eva.dtholiday.commons.dao.req;

import lombok.Data;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/28 2:10
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class BasePage {
    /**
     * 分页参数：每页大小
     */
    private int size;

    /**
     * 分页参数：当前页码
     */
    private int current;
}
