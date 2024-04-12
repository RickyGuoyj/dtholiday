package com.eva.dtholiday.commons.dao.resp.portal;

import lombok.Data;

@Data
public class TagResp {
    /**
     * 标签编码
     */
    private Integer tag_index_code;

    /**
     * 标签名称
     */
    private String tag_name;

    /**
     * 标签图片URL
     */
    private String tag_image;
}
