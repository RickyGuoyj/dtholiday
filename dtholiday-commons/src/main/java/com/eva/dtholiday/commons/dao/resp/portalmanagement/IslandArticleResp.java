package com.eva.dtholiday.commons.dao.resp.portalmanagement;

import com.eva.dtholiday.commons.dao.dto.FileInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/14 9:37
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class IslandArticleResp {

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
    private Integer type;

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

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
