package com.eva.dtholiday.commons.dao.req.portalmanagement;

import com.eva.dtholiday.commons.dao.dto.FileInfo;
import lombok.Data;

import java.util.List;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/14 4:25
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class IslandArticleReq {

    private Integer articleIndexCode;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 0-探索马代 1-岛屿文章（最新消息） 2-灯塔游记
     */
    private String type;

    /**
     * 内容
     */
    private String content;

    /**
     * 是否显示 0-不显示 1-显示
     */
    private String isDisplay = "1";

    /**
     * 关联图片
     */
    private List<FileInfo> pictures;

    /**
     * 岛屿管理主键
     */
    private Integer islandIndexCode;

    /**
     * 岛屿管理名称
     */
    private String islandCnName;

    /**
     * 链接
     */
    private String links;


}
