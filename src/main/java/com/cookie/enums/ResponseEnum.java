package com.cookie.enums;

import lombok.Getter;

/**
 * @auther hff
 * @time 2021/5/2 - 13:17
 **/
@Getter
public enum  ResponseEnum {

    SUCCESS(0,"成功"),

    USER_NOT_LOGIN(1,"用户未登录"),

    USERNAME_OR_PASSWORD_ERROR(2,"用户名或密码错误"),

    ERROR(-1,"服务器异常"),


    ;

    Integer code;

    String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
