package com.bwei.oracle_coundday0421.vo;

import com.bwei.oracle_coundday0421.bean.TBook;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @User feifei
 * @ClassName BookVo  类名
 * @Author Administrator  作者
 * @Date 2020-04-23 15:45  时间
 * @Version 1.0 版本
 */
@Data
public class BookVo extends TBook {
    private Date borrowingNewTime;

    private String flag;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;
}
