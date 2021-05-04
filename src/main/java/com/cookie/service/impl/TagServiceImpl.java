package com.cookie.service.impl;

import com.cookie.enums.ResponseEnum;
import com.cookie.mapper.TagMapper;
import com.cookie.pojo.Tag;
import com.cookie.service.ITagService;
import com.cookie.vo.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cookie.consts.ConstsNum.PAGE_SIZE;

/**
 * @auther hff
 * @time 2021/5/2 - 16:49
 **/
@Service
public class TagServiceImpl implements ITagService {

    @Autowired
    private TagMapper tagMapper;


    @Override
    public ResponseVo selectAll(Integer pageNum) {
        PageHelper.startPage(pageNum,PAGE_SIZE);
        List<Tag> tags = tagMapper.selectAll();
        PageInfo<Tag> pageInfo = new PageInfo<>(tags);
        if(tags == null) {
            return ResponseVo.error(ResponseEnum.ERROR);

        }
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo insertTag(Tag tag) {
        int insert = tagMapper.insert(tag);
        if(insert > 0) {
            return ResponseVo.success();
        }
        return ResponseVo.error(ResponseEnum.ERROR);
    }

    @Override
    public String slectById(Long id) {
        Tag tag = tagMapper.selectByPrimaryKey(id);
        return tag.getName();
    }

    @Override
    public Boolean deleteById(Long id) {
        int i = tagMapper.deleteByPrimaryKey(id);
        if(i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Tag> selectAllTag() {
        List<Tag> tags = tagMapper.selectAll();
        return tags;
    }

    @Override
    public List<Tag> selectLimit() {
        return tagMapper.selectTagBlog();
    }
}
