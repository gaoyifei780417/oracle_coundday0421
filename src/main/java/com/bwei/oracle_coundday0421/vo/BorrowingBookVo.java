package com.bwei.oracle_coundday0421.vo;

import com.bwei.oracle_coundday0421.bean.TBorrowingBook;
import lombok.Data;

/**
 * @User feifei
 * @ClassName BorrowingBookVo  类名
 * @Author Administrator  作者
 * @Date 2020-04-24 20:52  时间
 * @Version 1.0 版本
 */
@Data
public class BorrowingBookVo extends TBorrowingBook {
    private String loginName;

    private String bookName;
}
