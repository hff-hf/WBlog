package com.cookie.vo;

import com.cookie.pojo.Type;
import com.cookie.pojo.User;
import lombok.Data;
import java.util.Date;

/**
 * @auther hff
 * @time 2021/5/3 - 8:28
 **/
@Data
public class BlogVo {

    private Long id;

    private String title;

    private String description;

    private User user;

    private Date updateTime;

    private Integer views;

    private Type type;

    private String firstPicture;

    private Boolean published;

    private Boolean recommend;

}
