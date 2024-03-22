package com.eva.dtholiday.commons.exception;

import lombok.Data;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/22 6:44
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Data
public class BusinessException extends RuntimeException {

    private String message;

    private String errorCode;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
