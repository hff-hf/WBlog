package com.cookie.controller.admin;

import com.cookie.pojo.Type;
import com.cookie.service.ITypeService;
import com.cookie.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @auther hff
 * @time 2021/5/2 - 18:17
 **/
@Controller
@RequestMapping("/admin")
@Slf4j
public class TypeController {


    @Autowired
    private ITypeService typeService;

    @GetMapping("/types")
    public String type(@RequestParam(required = false,defaultValue = "1") Integer pageNum, Model model) {
        ResponseVo responseVo = typeService.selectAll(pageNum);
        model.addAttribute("type",responseVo);
        return "admin/types";
    }


    @GetMapping("/types/input")
    public String type_input() {
        return "admin/types-input";
    }


    @PostMapping("/types")
    public String addType(Type type, RedirectAttributes attributes) {
        ResponseVo responseVo = typeService.insertTag(type);
        if(responseVo.getCode() != 0) {
            attributes.addFlashAttribute("message","操作失败");
            return "redirect:/admin/types";
        }
        attributes.addFlashAttribute("message","操作成功");
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String editdTag(@PathVariable Long id, Model model) {
        String typeName = typeService.slectById(id);
        log.info("typeName:{}",typeName);
        model.addAttribute("typeName",typeName);
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/delete")
    public String deleteTag(@PathVariable Long id , RedirectAttributes attributes) {
        Boolean del = typeService.deleteById(id);
        if(del) {
            attributes.addFlashAttribute("message","操作成功");
            return "redirect:/admin/types";
        }
        attributes.addFlashAttribute("message","操作失败");
        return "redirect:/admin/types";
    }



}
