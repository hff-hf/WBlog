package com.cookie.service;

import com.cookie.pojo.Tag;
import com.cookie.vo.ResponseVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @auther hff
 * @time 2021/5/2 - 16:48
 **/
public interface ITagService {

    ResponseVo selectAll(Integer pageNum);

    ResponseVo insertTag(Tag tag);

    String slectById(Long id);

    Boolean deleteById(Long id);

    List<Tag> selectAllTag();

    List<Tag> selectLimit();
}
