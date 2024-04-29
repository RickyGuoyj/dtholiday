package com.eva.dtholiday.commons.dao.req.productManagement;

import lombok.Data;

import java.util.List;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/29 0:54
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class IslandHotelQueryReq {

    private Integer islandHotelId;

    private List<Integer> islandHotelIdList;
}
