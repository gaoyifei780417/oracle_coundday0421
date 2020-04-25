package com.bwei.oracle_coundday0421.dao;


import com.bwei.oracle_coundday0421.bean.TBook;
import com.bwei.oracle_coundday0421.bean.TBookExample;
import com.bwei.oracle_coundday0421.vo.BookVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TBookMapper {
    int countByExample(TBookExample example);

    int deleteByExample(TBookExample example);

    int deleteByPrimaryKey(String id);

    int insert(TBook record);

    int insertSelective(TBook record);

    List<TBook> selectByExample(TBookExample example);

    TBook selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TBook record, @Param("example") TBookExample example);

    int updateByExample(@Param("record") TBook record, @Param("example") TBookExample example);

    int updateByPrimaryKeySelective(TBook record);

    int updateByPrimaryKey(TBook record);

    List<BookVo> list(BookVo tBook);

    int updateFlagAndBorrowingCount(String bookId);
}