package com.bwei.oracle_coundday0421.controller;

import com.bwei.oracle_coundday0421.bean.TBook;
import com.bwei.oracle_coundday0421.bean.TUser;
import com.bwei.oracle_coundday0421.service.BookService;
import com.bwei.oracle_coundday0421.utils.StaticFlag;
import com.bwei.oracle_coundday0421.vo.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @User feifei
 * @ClassName BookController  类名
 * @Author Administrator  作者
 * @Date 2020-04-22 19:08  时间
 * @Version 1.0 版本
 */
@Controller
@RequestMapping("/book")
public class BookController {

    //redis
    @Autowired
    private RedisTemplate redisTemplate;


    /*磁盘上的路径*/
    @Value("${file.upload.path}")
    private String filePath ;

    @Autowired
    private BookService bookService;
    @RequestMapping("/list")
    public String list(BookVo tBook, Model model){
        List<BookVo> alist=bookService.list(tBook);
        model.addAttribute("alist",alist);
        model.addAttribute("tBook",tBook);
        return "book_list";
    }


    @RequestMapping("/lendBook")
    public String lendBook(String bookid, HttpServletRequest request){
        //需要记录那个用户操作的
        TUser user = (TUser) request.getSession().getAttribute(StaticFlag.USERUNFO);
        try {
            if (bookService.lendBook(user,bookid) >0){
                return "redirect:/book/list";
            }
        }catch (Exception e){
            e.printStackTrace();
            throw   new RuntimeException("数据添加异常");
        }
        return null;

    }

    /**
     * 归还
     */
    @RequestMapping("/restorationBook")
    @ResponseBody
    public int restorationBook(String bookid){
        if (!StringUtils.isEmpty(bookid)) { //如果id 不为空，则更新数据
           int flag = bookService.restorationBook(bookid);
           return flag;
        }
        return 0;
    }


    //添加跳转页面
    @RequestMapping("/toadd")
    public String toadd(){
        return "book_add";
    }

    @RequestMapping("/add")
    public String add(@RequestParam("file")MultipartFile file, TBook tBook,Model model){
        if(file.getSize()>0){
            //对图片进行上传并且赋值
            String picuil = StaticFlag.uploadUtils(file, filePath);
            tBook.setPicUrl(picuil);
        }
        int flag=bookService.add(tBook);
        if(flag>0){
            return "redirect:/book/list";
        }else{
            model.addAttribute("msg","添加失败");
            return "book_add";
        }
    }

    /**
     * 点赞的功能
     */
    @RequestMapping("/updown")
    @ResponseBody
    public String  updown(String id,HttpServletRequest request){
        //判断当前用户是否登录
        TUser user = (TUser) request.getSession().getAttribute(StaticFlag.USERUNFO);
        if(user!=null){
            //判断当前用户是否点赞过
            Boolean aBoolean = redisTemplate.hasKey(user.getId() + "updown_" + id);
            if(!aBoolean){
                redisTemplate.opsForValue().increment("updown_"+id,1);
                return "yes";
            }else{
                return "no";
            }

        }else{
            return "notLogin";
        }
    }

    @RequestMapping("/toQueryBookInfo")
    public String toQueryBookInfo(String bookid,Model model){
        model.addAttribute("bookid",bookid);
        //统计点击量
        redisTemplate.opsForValue().increment(bookid,1);
        return "book_info";
    }

    @RequestMapping("/queryBookInfo")
    @ResponseBody
    public TBook queryBookInfo(String id){
        TBook tBook=bookService.queryBookInfo(id);
        Boolean aBoolean = redisTemplate.hasKey(id);
        if(aBoolean){
            //如果id存在redis中则去获取它的点击量
            Long count = (Long) redisTemplate.opsForValue().get(id);
            tBook.setTraffic(count);//赋值
        }else {
            //如果不存在,则设置当前的点击量为0
            tBook.setTraffic((long)0);
        }
        return tBook;
    }


}
