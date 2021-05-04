package com.cookie.service;

import com.cookie.form.BlogPut;
import com.cookie.pojo.Blog;
import com.cookie.vo.ResponseVo;

import java.util.List;
import java.util.Map;

/**
 * @auther hff
 * @time 2021/5/3 - 9:02
 **/
public interface IBlogService {

    ResponseVo selectAll(Integer pageNum);

    Boolean deleteById(Long id);

    int insertBlog(BlogPut blogPut);

    ResponseVo selectBlogTypeUser(Integer pageNum);

    List<Blog> lastNewRecomment();

    Blog selectBlogUserByBlogId(Integer id);

    int updateViews(Integer id);

    ResponseVo selectBlogTypeUserByTypeId(Integer id,Integer pageNum);

    ResponseVo selectBlogTypeUserByTagId(Integer id, Integer pageNum);

    int countBlog();

    Map<String,List<Blog>> selectAllNotPageNum();

    ResponseVo selectBlogTypeUserLikeTitle(String s);

}
