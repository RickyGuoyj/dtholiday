package com.eva.dtholiday.commons.dao.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/31 21:17
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Getter
public class PasswordReq {

    @NotBlank(message = "用户名称不能为空")
    @ApiModelProperty( name="userName",notes = "用户名称")
    private String userName;

    @NotBlank(message = "旧密码不能为空")
    @ApiModelProperty( name="oldPwd",notes = "旧密码")
    private String oldPwd;

    @NotBlank(message = "新密码不能为空")
    @ApiModelProperty( name="newPwd",notes = "新密码")
    private String newPwd;

    @NotBlank(message = "确认密码不能为空")
    @ApiModelProperty( name="confirmPwd",notes = "确认密码")
    private String confirmPwd;

}
