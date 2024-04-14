package com.eva.dtholiday.commons.dao.resp.portalmanagement;

import java.util.List;

import com.eva.dtholiday.commons.dao.dto.FileInfo;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandQuotation;
import com.eva.dtholiday.commons.dao.entity.portalmanagement.IslandTag;

import lombok.Data;

@Data
public class IslandManagementQueryDetailResp {
    /**
     * 岛屿中文名
     */
    private String islandCnName;
    /**
     * 岛屿英文名
     */
    private String islandEnName;

    private int islandIndexCode;
    /**
     * 岛屿描述
     */
    private String islandDesc;
    /**
     * 岛屿资料连接
     */
    private String islandFile;
    /**
     * 岛屿图片
     */
    private FileInfo islandImage;
    /**
     * 岛屿介绍
     */
    private String islandIntro;
    /**
     * 岛屿pdf报价单
     */
    private List<IslandQuotation> islandQuotationPdfList;
    /**
     * 岛屿标签
     */
    private List<IslandTag> islandTagList;
}
