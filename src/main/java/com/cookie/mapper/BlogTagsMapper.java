package com.cookie.mapper;

import com.cookie.pojo.Blog;
import com.cookie.pojo.BlogTags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;
@Mapper
public interface BlogTagsMapper {
    int insert(BlogTags record);

    int insertSelective(BlogTags record);

    int insertEach(@Param("blogId") Long blogId, @Param("tagIdSet")Set<Long> tagIdSet);

}