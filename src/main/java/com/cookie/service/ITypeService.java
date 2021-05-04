package com.cookie.service;

import com.cookie.pojo.Type;
import com.cookie.vo.ResponseVo;

import java.util.List;

/**
 * @auther hff
 * @time 2021/5/2 - 18:21
 **/
public interface ITypeService {

    ResponseVo selectAll(Integer pageNum);

    ResponseVo insertTag(Type type);

    String slectById(Long id);

    Boolean deleteById(Long id);

    List<Type> selectAllNotPage();

    List<Type> selectLimit();
}
