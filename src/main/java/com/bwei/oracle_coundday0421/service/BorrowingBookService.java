package com.bwei.oracle_coundday0421.service;

import com.bwei.oracle_coundday0421.dao.TBorrowingBookMapper;
import com.bwei.oracle_coundday0421.vo.BorrowingBookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @User feifei
 * @ClassName BorrowingBookService  类名
 * @Author Administrator  作者
 * @Date 2020-04-24 20:50  时间
 * @Version 1.0 版本
 */
@Service
public class BorrowingBookService {
    @Autowired
    private TBorrowingBookMapper tBorrowingBookMapper;

    public List<BorrowingBookVo> queryBookBorrowingList(String bookid) {
        return tBorrowingBookMapper.queryBookBorrowingList(bookid);
    }
}
