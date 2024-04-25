package com.eva.dtholiday.system.service.portalmanagement;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandArticle;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandManagement;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandArticleQueryDto;
import com.eva.dtholiday.commons.dao.req.portalmanagement.IslandArticleReq;

import java.util.List;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/14 4:22
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
public interface IslandArticleService {
    ResponseApi islandArticleAdd(IslandArticleReq req);

    ResponseApi islandArticleDelete(IslandArticleQueryDto req);

    ResponseApi islandArticleUpdate(IslandArticleReq req);

    ResponseApi islandArticleQueryDetail(Integer articleIndexCode);

    ResponseApi islandArticleQueryList(IslandArticleQueryDto req);
}
