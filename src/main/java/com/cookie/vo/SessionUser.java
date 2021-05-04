package com.cookie.vo;

import lombok.Data;

import java.util.Date;

/**
 * @auther hff
 * @time 2021/5/2 - 13:29
 **/
@Data
public class SessionUser {

    private Long id;

    private String avatar;

    private String email;

    private String nickname;

    private Integer type;

    private String username;
}
