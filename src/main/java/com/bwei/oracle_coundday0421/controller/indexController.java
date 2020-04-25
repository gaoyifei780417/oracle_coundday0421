package com.bwei.oracle_coundday0421.controller;

import com.bwei.oracle_coundday0421.bean.TUser;
import com.bwei.oracle_coundday0421.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @User feifei
 * @ClassName indexController  类名
 * @Author Administrator  作者
 * @Date 2020-04-22 17:07  时间
 * @Version 1.0 版本
 */
@Controller
@RequestMapping("/users")
public class indexController {
    @Autowired
    private UserService userService;
    @RequestMapping("/")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/toTest")
    public String toTest(){
        return "test";
    }

    @RequestMapping("/login")
    public String login(TUser tUser, Model model, HttpServletRequest request){
        TUser u=  userService.login(tUser);
        if(null!=u){
            request.getSession().setAttribute("users",u);
            return "redirect:/book/list";
        }else{
            return "login";
        }

    }
}
