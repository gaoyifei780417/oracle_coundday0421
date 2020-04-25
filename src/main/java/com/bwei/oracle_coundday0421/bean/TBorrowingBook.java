package com.bwei.oracle_coundday0421.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TBorrowingBook {
    private String id;

    private String bookId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date borrowing;

    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId == null ? null : bookId.trim();
    }

    public Date getBorrowing() {
        return borrowing;
    }

    public void setBorrowing(Date borrowing) {
        this.borrowing = borrowing;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}