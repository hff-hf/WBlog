package com.cookie.service.impl;

import com.cookie.enums.ResponseEnum;
import com.cookie.mapper.TypeMapper;
import com.cookie.pojo.Type;
import com.cookie.service.ITypeService;
import com.cookie.vo.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cookie.consts.ConstsNum.PAGE_SIZE;

/**
 * @auther hff
 * @time 2021/5/2 - 18:22
 **/
@Service
public class TypeServiceImpl implements ITypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public ResponseVo selectAll(Integer pageNum) {
        PageHelper.startPage(pageNum,PAGE_SIZE);
        List<Type> types = typeMapper.selectAll();
        PageInfo<Type> pageInfo = new PageInfo<>(types);
        if(types == null) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo insertTag(Type type) {
        int i = typeMapper.insert(type);
        if(i > 0) {
            return ResponseVo.success();
        }
        return ResponseVo.error(ResponseEnum.ERROR);
    }

    @Override
    public String slectById(Long id) {
        Type type = typeMapper.selectByPrimaryKey(id);
        return type.getName();
    }

    @Override
    public Boolean deleteById(Long id) {
        int i = typeMapper.deleteByPrimaryKey(id);
        if(i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Type> selectAllNotPage() {
        List<Type> types = typeMapper.selectAll();
        return types;
    }

    @Override
    public List<Type> selectLimit() {

        return typeMapper.selectTypeBlog();
    }


}
