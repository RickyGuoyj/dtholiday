package com.eva.dtholiday.commons.dao.resp.portal;

import lombok.Data;

import java.util.List;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/13 2:41
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class IslandListResp {

    private Integer island_id;

    private String island_desc;

    private String island_image;

    private String island_name;

    private String island_intro;

    private List<Integer> island_tags;
}
