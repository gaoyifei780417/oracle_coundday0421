package com.bwei.oracle_coundday0421.bean;

import lombok.Data;

import java.util.Date;
@Data
public class TBook {
    private String id;

    private String bookName;

    private String bookIntroduce;

    private String bookAuthor;

    private Integer borrowingCount;

    private String borrowingFlag;

    private String picUrl;

    private Date createTime;

    private Long traffic;


}