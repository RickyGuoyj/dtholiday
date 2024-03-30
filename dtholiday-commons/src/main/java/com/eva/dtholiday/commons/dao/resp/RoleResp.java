package com.eva.dtholiday.commons.dao.resp;

import com.eva.dtholiday.commons.dao.dto.MenuAsyncTree;
import lombok.Data;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/30 12:52
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class RoleResp {

    private String name;

    private String code;

    private MenuAsyncTree menuAsyncTree;
}
