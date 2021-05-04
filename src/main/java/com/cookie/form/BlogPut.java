package com.cookie.form;

import com.cookie.pojo.Tag;
import com.cookie.pojo.Type;
import com.cookie.pojo.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @auther hff
 * @time 2021/5/3 - 10:01
 **/
@Data
public class BlogPut {

    private Boolean appreciation;

    private Boolean commentabled;

    private Date createTime;

    private String description;

    private String firstPicture;

    private String flag;

    private Boolean published;

    private Boolean recommend;

    private Boolean shareStatement;

    private String title;

    private Date updateTime;

    private String content;

    private Long userId;

    private Long typeId;

    private Long[] tagIds;


}
