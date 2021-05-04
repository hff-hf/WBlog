package com.cookie.mapper;

import com.cookie.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKeyWithBLOBs(Blog record);

    int updateByPrimaryKey(Blog record);

    List<Blog> selectAll();

    List<Blog> selectAlljustBlog();

    List<Blog> selectBlogTypeUser();

    List<Blog> lastNewRecoment();

    Blog selectBlogUserByBlogId(Integer id);

    List<Blog> selectBlogTypeUserByTypeId(Integer id);

    int updateViews(Integer id);

    List<Blog> selectBlogTypeUserByTagId(Integer id);

    int countBlog();

    List<Blog> selectBlogTypeUserLikeTitle(String s);
}