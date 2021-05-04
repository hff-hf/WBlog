package com.cookie.vo;

import com.cookie.pojo.Tag;
import com.cookie.pojo.Type;
import com.cookie.pojo.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @auther hff
 * @time 2021/5/3 - 13:17
 **/
@Data
public class ViewBlog {

    private Long id;

    private String title;

    private String description;

    private User user;

    private Date updateTime;

    private Integer views;

    private Type type;

    private String firstPicture;

}
