package com.cookie.service.impl;

import com.cookie.form.BlogPut;
import com.cookie.mapper.BlogMapper;
import com.cookie.mapper.BlogTagsMapper;
import com.cookie.pojo.Blog;
import com.cookie.service.IBlogService;
import com.cookie.vo.ResponseVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.BadLocationException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.cookie.consts.ConstsNum.PAGE_SIZE;

/**
 * @auther hff
 * @time 2021/5/3 - 9:02
 **/
@Service
public class BlogServiceImpl implements IBlogService {

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private BlogTagsMapper blogTagsMapper;

    @Override
    public ResponseVo selectAll(Integer pageNum) {
        PageHelper.startPage(pageNum,PAGE_SIZE);
        List<Blog> blogs = blogMapper.selectAll();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public Boolean deleteById(Long id) {
        int i = blogMapper.deleteByPrimaryKey(id);
        if(i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int insertBlog(BlogPut blogPut) {
        Blog blog = new Blog();
        blog.setViews(0);
        BeanUtils.copyProperties(blogPut,blog);
        int count = blogMapper.insertSelective(blog);
        if(count > 0) {
            Long id = blog.getId();
            Long[] tagIds = blogPut.getTagIds();
            Set<Long> set = new HashSet<>(Arrays.asList(tagIds));
            blogTagsMapper.insertEach(id,set);
        }
        return count;
    }

    @Override
    public ResponseVo selectBlogTypeUser(Integer pageNum) {
        PageHelper.startPage(pageNum,PAGE_SIZE);
        List<Blog> blogs = blogMapper.selectBlogTypeUser();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public List<Blog> lastNewRecomment() {
        return blogMapper.lastNewRecoment();

    }

    @Override
    public Blog selectBlogUserByBlogId(Integer id) {
        return blogMapper.selectBlogUserByBlogId(id);
    }

    @Override
    public int updateViews(Integer id) {
        return blogMapper.updateViews(id);
    }

    @Override
    public ResponseVo selectBlogTypeUserByTypeId(Integer id,Integer pageNum) {
        PageHelper.startPage(pageNum,PAGE_SIZE);
        List<Blog> blogs = blogMapper.selectBlogTypeUserByTypeId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo selectBlogTypeUserByTagId(Integer id, Integer pageNum) {
        PageHelper.startPage(pageNum,PAGE_SIZE);
        List<Blog> blogs = blogMapper.selectBlogTypeUserByTagId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public int countBlog() {
        return blogMapper.countBlog();
    }

    @Override
    public Map<String,List<Blog>> selectAllNotPageNum() {
        List<Blog> blogs = blogMapper.selectAlljustBlog();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String,List<Blog>> map = new HashMap<>();
        for (Blog blog : blogs) {
            String date = sf.format(blog.getUpdateTime());
            String year = date.substring(0, 4);
            if(map.containsKey(year)) {
                map.get(year).add(blog);
            }else {
                List<Blog> list = new ArrayList<>();
                list.add(blog);
                map.put(year,list);
            }
        }
        return map;
    }

    @Override
    public ResponseVo selectBlogTypeUserLikeTitle(String s) {
        PageHelper.startPage(1,50);
        List<Blog> list = blogMapper.selectBlogTypeUserLikeTitle(s);
        PageInfo<Blog> pageInfo = new PageInfo<>(list);
        return ResponseVo.success(pageInfo);
    }
}
