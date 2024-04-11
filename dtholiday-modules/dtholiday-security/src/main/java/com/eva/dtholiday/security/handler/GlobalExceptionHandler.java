package com.eva.dtholiday.security.handler;

import com.eva.dtholiday.commons.api.ResponseApi;
import com.eva.dtholiday.commons.enums.BusinessErrorCodeEnum;
import com.eva.dtholiday.commons.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/4/6 19:54
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常.
     *
     * @param e the e
     * @return R
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseApi handleGlobalException(Exception e) {
        e.printStackTrace();
        log.error("全局异常信息 ex:{},message:{}", e, e.getMessage(), e);
        return ResponseApi.error(BusinessErrorCodeEnum.FAIL.getCode(), BusinessErrorCodeEnum.FAIL.getCode());
    }


    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseApi handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        //全局异常错误码
        String code = BusinessErrorCodeEnum.FAIL.getCode();
        String firstMsg = null;
        String firstField = null;
        if (!CollectionUtils.isEmpty(errors)) {
            int first = 0;
            for (int i = 0; i < errors.size(); ++i) {
                ObjectError error = (ObjectError) errors.get(i);
                String message = error.getDefaultMessage();
                String field = null;
                if (error instanceof FieldError) {
                    field = ((FieldError) error).getField();
                    if (i == first) {
                        firstMsg = message;
                        firstField = field;
                    }
                }

                log.error("MethodArgumentNotValidException info code:{} field:{} msg:{}", code, firstField, firstMsg);
            }
        }
        return ResponseApi.error(null, code, firstMsg);
    }


    /**
     * 拦截业务异常
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BusinessException.class)
    public ResponseApi handleBusinessException(BusinessException businessException) {
        log.error("业务异常 errorCode: {} msg:{}", businessException.getErrorCode(), businessException.getMessage());
        return ResponseApi.error(null, businessException.getErrorCode(), businessException.getMessage());
    }
}
