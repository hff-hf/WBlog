package com.cookie.controller.admin;

import com.cookie.pojo.Tag;
import com.cookie.service.ITagService;
import com.cookie.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.ldap.PagedResultsControl;

/**
 * @auther hff
 * @time 2021/5/2 - 16:42
 **/
@Controller
@Slf4j
@RequestMapping("/admin")
public class TagController {


    @Autowired
    private ITagService tagService;

    @GetMapping("/tags")
    public String tag(@RequestParam(required = false,defaultValue = "1") Integer pageNum, Model model) {
        ResponseVo responseVo = tagService.selectAll(pageNum);
        model.addAttribute("tags",responseVo);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String tag_input() {
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String addTag(Tag tag, RedirectAttributes attributes) {
        ResponseVo responseVo = tagService.insertTag(tag);
        if(responseVo.getCode() != 0) {
            attributes.addFlashAttribute("message","操作失败");
            return "redirect:/admin/tags";
        }
        attributes.addFlashAttribute("message","操作成功");
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/input")
    public String editdTag(@PathVariable Long id,Model model) {
        String tagName = tagService.slectById(id);
        model.addAttribute("tagName",tagName);
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/delete")
    public String deleteTag(@PathVariable Long id , RedirectAttributes attributes) {
        Boolean del = tagService.deleteById(id);
        if(del) {
            attributes.addFlashAttribute("message","操作成功");
            return "redirect:/admin/tags";
        }
        attributes.addFlashAttribute("message","操作失败");
        return "redirect:/admin/tags";
    }



}
