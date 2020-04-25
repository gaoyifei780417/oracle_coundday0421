package com.bwei.oracle_coundday0421.controller;

import com.bwei.oracle_coundday0421.service.BorrowingBookService;
import com.bwei.oracle_coundday0421.vo.BorrowingBookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @User feifei
 * @ClassName BorrowingBookController  类名
 * @Author Administrator  作者
 * @Date 2020-04-24 20:46  时间
 * @Version 1.0 版本
 */
@Controller
@RequestMapping("/borro")
public class BorrowingBookController {
    @Autowired
    private  BorrowingBookService borrowingBookService;

    @RequestMapping("/queryBookBorrowingList")
    @ResponseBody
    public List<BorrowingBookVo>  queryBookBorrowingList(String bookid){
        List<BorrowingBookVo> list= borrowingBookService.queryBookBorrowingList(bookid);
        return list;
    }
}
