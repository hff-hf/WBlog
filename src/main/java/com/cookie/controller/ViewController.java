package com.cookie.controller;

import com.cookie.pojo.Blog;
import com.cookie.pojo.Tag;
import com.cookie.pojo.Type;
import com.cookie.service.IBlogService;
import com.cookie.service.ITagService;
import com.cookie.service.ITypeService;
import com.cookie.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @auther hff
 * @time 2021/5/3 - 12:52
 **/
@Controller
@Slf4j
public class ViewController {

    @Autowired
    private IBlogService blogService;
    @Autowired
    private ITypeService typeService;
    @Autowired
    private ITagService tagService;


    @GetMapping("/")
    public String index(@RequestParam(required = false,defaultValue = "1") Integer pageNum, Model model) {
        ResponseVo responseVo = blogService.selectBlogTypeUser(pageNum);
        List<Blog> newRecomment = blogService.lastNewRecomment();
        List<Type> types = typeService.selectLimit();
        List<Tag> tags = tagService.selectLimit();
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        model.addAttribute("blogs",responseVo);
        log.info("content:{}",responseVo.getData().toString());
        model.addAttribute("newRecomment",newRecomment);
        return "index";
    }


    @GetMapping("/types")
    public String type(@RequestParam(required = false,defaultValue = "1")Integer pageNum, Model model) {
        List<Type> types = typeService.selectLimit();
        ResponseVo responseVo = blogService.selectBlogTypeUser(pageNum);
        model.addAttribute("blogs",responseVo);
        model.addAttribute("types",types);
        model.addAttribute("activeTypeId",1);
        return "types";
    }

    @GetMapping("/types/{id}")
    public String type_parm(@PathVariable Integer id,@RequestParam(required = false,defaultValue = "1")Integer pageNum,Model model) {
        ResponseVo responseVo = blogService.selectBlogTypeUserByTypeId(id, pageNum);
        List<Type> types = typeService.selectLimit();
        model.addAttribute("blogs",responseVo);
        model.addAttribute("types",types);
        model.addAttribute("activeTypeId",id);
        return "types";
    }

    @GetMapping("/tags")
    public String tag(@RequestParam(required = false,defaultValue = "1") Integer pageNum,Model model) {
        List<Tag> tags = tagService.selectLimit();
        ResponseVo responseVo = blogService.selectBlogTypeUser(pageNum);
        model.addAttribute("blogs",responseVo);
        model.addAttribute("tags",tags);
        model.addAttribute("activeTypeId",1);
        return "tags";
    }

    @GetMapping("/tags/{id}")
    public String tag_parm(@PathVariable Integer id,@RequestParam(required = false,defaultValue = "1")Integer pageNum,Model model) {
        ResponseVo responseVo = blogService.selectBlogTypeUserByTagId(id, pageNum);
        List<Tag> tags = tagService.selectLimit();
        model.addAttribute("blogs",responseVo);
        model.addAttribute("tags",tags);
        model.addAttribute("activeTypeId",id);
        return "tags";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Integer id,Model model) {
        Blog blog = blogService.selectBlogUserByBlogId(id);
        log.info("connet:{}",blog.getContent());
        int i = blogService.updateViews(id);
        model.addAttribute("blog",blog);
        return "blog";
    }


    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/archive")
    public String archives(Model model) {
        int count = blogService.countBlog();
        Map<String, List<Blog>> map = blogService.selectAllNotPageNum();
        model.addAttribute("archiveMap",map);
        model.addAttribute("blogCount",count);
        return "archives";
    }

    @PostMapping("/search")
    public String search(@RequestParam String query,Model model) {
        ResponseVo responseVo = blogService.selectBlogTypeUserLikeTitle("%"+query+"%");
        model.addAttribute("blogs",responseVo);
        return "search";
    }

    @GetMapping("/test")
    @ResponseBody
    public Map<String, List<Blog>> test() {
        return blogService.selectAllNotPageNum();

    }

}
