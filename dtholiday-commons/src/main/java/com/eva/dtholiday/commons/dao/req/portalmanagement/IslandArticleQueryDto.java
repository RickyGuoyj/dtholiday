package com.eva.dtholiday.commons.dao.req.portalmanagement;

import lombok.Data;

import java.util.List;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/25 13:13
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class IslandArticleQueryDto {

    private List<Integer> articleIndexCodes;

    private List<Integer> type;

    private String title;

    private Integer articleIndexCode;
}
