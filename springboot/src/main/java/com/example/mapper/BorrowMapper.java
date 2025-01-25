package com.example.mapper;

import com.example.entity.Borrow;

import java.util.List;

/**
 * 操作borrow相关数据接口
*/
public interface BorrowMapper {

    /**
      * 新增
    */
    int insert(Borrow borrow);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Borrow borrow);

    /**
      * 根据ID查询
    */
    Borrow selectById(Integer id);

    /**
      * 查询所有
    */
    List<Borrow> selectAll(Borrow borrow);

}