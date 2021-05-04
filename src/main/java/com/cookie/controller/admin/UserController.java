package com.cookie.controller.admin;

import com.cookie.service.IUserService;
import com.cookie.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @auther hff
 * @time 2021/5/2 - 11:21
 **/
@Controller
@Slf4j
@RequestMapping("/admin")
public class UserController {


    @Autowired
    private IUserService userService;

    @GetMapping
    public String login() {
        return "admin/login";
    }

    @PostMapping("/check")
    public String checkToIndex(String username, String password, RedirectAttributes redirectAttributes, HttpSession session) {
        ResponseVo vo = userService.selectUserByUsernameAndPassword(username, password);
        if(vo.getData() == null) {
            redirectAttributes.addFlashAttribute("message", vo.getMsg());
            return "redirect:/admin";
        }
        session.setAttribute("user",vo.getData());
        return "admin/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
