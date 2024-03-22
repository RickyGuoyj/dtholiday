package com.eva.dtholiday.commons.api;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/19 14:55
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class ResponseApi<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标记
     */
    private static String SUCCESS = "0";
    /**
     * 失败标记
     */
    private static String FAIL = "1";
    /**
     * 未认证
     */
    private static String UNAUTH = "401";

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String msg;

    @Getter
    @Setter
    private T data;

    public static <T> ResponseApi<T> ok() {
        return restResult(null, SUCCESS, "success");
    }


    public static <T> ResponseApi<T> ok(T data) {
        return restResult(data, SUCCESS, "success");
    }

    public static <T> ResponseApi<T> ok(T data, String msg) {
        return restResult(data, SUCCESS, msg);
    }


    public static <T> ResponseApi<T> error() {
        return restResult(null, FAIL, "fail");
    }

    public static <T> ResponseApi<T> error(String code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> ResponseApi<T> error(T data, String code, String msg) {
        return restResult(data, code, msg);
    }

    public static <T> ResponseApi<T> error(String msg) {
        return restResult(null, FAIL, msg);
    }

    public static <T> ResponseApi<T> error(T data) {
        return restResult(data, FAIL, null);
    }

    public static <T> ResponseApi<T> error(T data, String msg) {
        return restResult(data, FAIL, msg);
    }

    public static <T> ResponseApi<T> unAuth(String msg) {
        return restResult(null, UNAUTH, msg);
    }


    private static <T> ResponseApi<T> restResult(T data, String code, String msg) {
        ResponseApi<T> apiResult = new ResponseApi<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
}