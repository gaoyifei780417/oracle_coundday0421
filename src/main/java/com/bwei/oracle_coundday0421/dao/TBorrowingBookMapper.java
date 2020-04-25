package com.bwei.oracle_coundday0421.dao;

import com.bwei.oracle_coundday0421.bean.TBorrowingBook;
import com.bwei.oracle_coundday0421.bean.TBorrowingBookExample;
import com.bwei.oracle_coundday0421.vo.BorrowingBookVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TBorrowingBookMapper {
    int countByExample(TBorrowingBookExample example);

    int deleteByExample(TBorrowingBookExample example);

    int deleteByPrimaryKey(String id);

    int insert(TBorrowingBook record);

    int insertSelective(TBorrowingBook record);

    List<TBorrowingBook> selectByExample(TBorrowingBookExample example);

    TBorrowingBook selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TBorrowingBook record, @Param("example") TBorrowingBookExample example);

    int updateByExample(@Param("record") TBorrowingBook record, @Param("example") TBorrowingBookExample example);

    int updateByPrimaryKeySelective(TBorrowingBook record);

    int updateByPrimaryKey(TBorrowingBook record);

    List<BorrowingBookVo> queryBookBorrowingList(String bookid);
}