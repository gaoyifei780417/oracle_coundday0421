package com.bwei.oracle_coundday0421.service;

import com.bwei.oracle_coundday0421.bean.TBook;
import com.bwei.oracle_coundday0421.bean.TBorrowingBook;
import com.bwei.oracle_coundday0421.bean.TUser;
import com.bwei.oracle_coundday0421.dao.TBookMapper;
import com.bwei.oracle_coundday0421.dao.TBorrowingBookMapper;
import com.bwei.oracle_coundday0421.utils.StaticFlag;
import com.bwei.oracle_coundday0421.vo.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @User feifei
 * @ClassName BookService  类名
 * @Author Administrator  作者
 * @Date 2020-04-22 19:10  时间
 * @Version 1.0 版本
 */
@Service
public class BookService {
    @Autowired
    private TBookMapper tBookMapper;

    @Autowired
    private TBorrowingBookMapper tBorrowingBookMapper;

    public List<BookVo> list(BookVo tBook) {
        return tBookMapper.list(tBook);
    }

    @Transactional
    public int lendBook(TUser user, String bookId) {
        //需要借阅记录表中,设置字段
        if(null!=user){
            //创建一个借阅表
            TBorrowingBook tBorrowingBook = new TBorrowingBook();
            //设置主键
            tBorrowingBook.setId(StaticFlag.queryID());
            //存入图书的id
            tBorrowingBook.setBookId(bookId);
            tBorrowingBook.setBorrowing(new Date()); //书籍的借阅时间
            //存入用户的id
            tBorrowingBook.setUserId(user.getId());
            //当前的记录插入到tBorrowingBook表中
            int insert = tBorrowingBookMapper.insert(tBorrowingBook);
            //去修改状态跟浏览量
            int flag=tBookMapper.updateFlagAndBorrowingCount(bookId);
            if(insert>0 && flag>0){
                //返回1
                return 1;
            }
        }
        //返回0
        return 0;
    }

    public int restorationBook(String bookid) {
        TBook tBook = new TBook();
        tBook.setBorrowingFlag("0");
        tBook.setId(bookid);
        //如果实体中某个字段不为空  则更新字段
        return tBookMapper.updateByPrimaryKeySelective(tBook);
    }

    public int add(TBook tBook) {
        tBook.setId(StaticFlag.queryID());
        tBook.setCreateTime(new Date());
        tBook.setBorrowingFlag("0");
        tBook.setBorrowingCount(0);
        return tBookMapper.insert(tBook);
    }

    public TBook queryBookInfo(String id) {
        return tBookMapper.selectByPrimaryKey(id);
    }
}
