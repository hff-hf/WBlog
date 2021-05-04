package com.cookie.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Type {

    private Long id;

    private String name;

    private List<Blog> blogs;

}