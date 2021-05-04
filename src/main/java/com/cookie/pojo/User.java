package com.cookie.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;

    private String avatar;

    private Date createTime;

    private String email;

    private String nickname;

    private String password;

    private Integer type;

    private Date updateTime;

    private String username;

}