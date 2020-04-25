package com.bwei.oracle_coundday0421.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.bwei.oracle_coundday0421.bean.TUser;
import com.bwei.oracle_coundday0421.service.UserService;
import com.bwei.oracle_coundday0421.utils.StaticFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @User feifei
 * @ClassName UserController  类名
 * @Author Administrator  作者
 * @Date 2020-04-22 15:46  时间
 * @Version 1.0 版本
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/tozhuce")
    public String tozhuce(){
        return "register";
    }

    /**
     * 注册功能
     * @param tUser
     * @return
     */
    @RequestMapping("/zhuce")
    public String zhuce(TUser tUser){
        //密码是加密的
        if(null!=tUser){
            //转换ID
            tUser.setId(RandomUtil.randomUUID().replace("-",""));
            //转换时间
            tUser.setCreateTime(new Date());
            //密码加密
            String md5 = SecureUtil.md5(tUser.getPassword());
            tUser.setPassword(md5);
        }
        int flag=userService.zhuce(tUser);
        if(flag>0){
            //判断是否大于0大于去登录页面
            return "login";
        }else{
            //否则去注册页面
            return "tozhuce";
        }

    }

    @RequestMapping("/checkPhone")
    @ResponseBody
    public String checkPhone(String phone){
        return JSON.toJSONString(userService.checkPhone(phone));
    }

    /**
     * 登录的校验
     */
    @RequestMapping(value = "/checkPasswordAndUserName",method = RequestMethod.POST)
     public String checkPasswordAndUserName(String loginName, String password, Model model, HttpServletRequest request){
        //如果登录成功跳转到图片列表页面
        if(!StringUtils.isEmpty(loginName) && !StringUtils.isEmpty(password)){
            TUser user =userService.checkPasswordAndUserName(loginName,password);
            if(null!=user){//用户名或密码错误
                request.getSession().setAttribute(StaticFlag.USERUNFO, user);
                return "redirect:/book/list";

            }else{
                model.addAttribute("msg","用户名或密码错误");
                return "login";
            }
        }else{
            model.addAttribute("msg","用户名或密码不能为空");
            //如果用户名或密码为空,返回到登录页面
            return "login";
        }

     }
}
