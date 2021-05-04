package com.cookie.controller.admin;

import com.cookie.form.BlogPut;
import com.cookie.pojo.Tag;
import com.cookie.pojo.Type;
import com.cookie.pojo.User;
import com.cookie.service.IBlogService;
import com.cookie.service.ITagService;
import com.cookie.service.ITypeService;
import com.cookie.vo.ResponseVo;
import com.cookie.vo.SessionUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @auther hff
 * @time 2021/5/3 - 8:24
 **/
@Controller
@RequestMapping("/admin")
@Slf4j
public class BlogController {


    @Autowired
    private IBlogService blogService;
    @Autowired
    private ITypeService typeService;
    @Autowired
    private ITagService tagService;

    @GetMapping("/blogs")
    public String blog_index(@RequestParam(required = false,defaultValue = "1") Integer pageNum, Model model) {
        ResponseVo responseVo = blogService.selectAll(pageNum);
        List<Type> types = typeService.selectAllNotPage();
        model.addAttribute("blogs",responseVo);
        model.addAttribute("types",types);
        return "admin/blogs";
    }

//    @GetMapping("/blogs/{id}/input")
//    public String editBlog(@PathVariable Integer id,Model model) {
//        List<Tag> tags = tagService.selectAllTag();
//        List<Type> types = typeService.selectAllNotPage();
//        model.addAttribute("types",types);
//        model.addAttribute("tags",tags);
//        return "admin/blogs-input";
//    }

    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes attributes) {
        Boolean aBoolean = blogService.deleteById(id);

        if(!aBoolean) {
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/blogs";
    }


    @GetMapping("/blogs/input")
    public String addBlog(Model model) {
        List<Tag> tags = tagService.selectAllTag();
        List<Type> types = typeService.selectAllNotPage();
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        return "admin/blogs-input";
    }


    @PostMapping("/blogs")
    public String put_blog(BlogPut blogPut, HttpSession session,RedirectAttributes attributes) {
        SessionUser user = (SessionUser) session.getAttribute("user");
        blogPut.setUserId(user.getId());
        blogPut.setUpdateTime(new Date());
        blogPut.setCreateTime(new Date());
        int i = blogService.insertBlog(blogPut);
        if(i < 1) {
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/blogs";
    }

}
