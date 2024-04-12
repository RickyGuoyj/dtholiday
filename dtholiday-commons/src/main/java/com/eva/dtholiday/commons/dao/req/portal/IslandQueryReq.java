package com.eva.dtholiday.commons.dao.req.portal;

import lombok.Getter;

import java.util.List;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/13 2:30
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Getter
public class IslandQueryReq {

    private List<Integer> tagIndexCodes;
}
