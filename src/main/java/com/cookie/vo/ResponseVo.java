package com.cookie.vo;

import com.cookie.enums.ResponseEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @auther hff
 * @time 2021/5/2 - 13:11
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVo <T> {

    private int code;

    private String msg;

    private T data;

    private ResponseVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResponseVo(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public static ResponseVo error(ResponseEnum responseEnum) {
        return new ResponseVo(responseEnum.getCode(),responseEnum.getMsg());
    }

    public static <T> ResponseVo success(ResponseEnum responseEnum, T data) {
        return new ResponseVo(responseEnum.getCode(),data);
    }

    public static <T> ResponseVo success(T data) {
        return new ResponseVo(ResponseEnum.SUCCESS.getCode(),data);
    }

    public static <T> ResponseVo success() {
        return new ResponseVo(ResponseEnum.SUCCESS.getCode(),ResponseEnum.SUCCESS.getMsg());
    }
}
