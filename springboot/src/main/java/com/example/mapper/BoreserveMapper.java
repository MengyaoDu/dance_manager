package com.example.mapper;

import com.example.entity.Boreserve;

import java.util.List;

/**
 * 操作boreserve相关数据接口
*/
public interface BoreserveMapper {

    /**
      * 新增
    */
    int insert(Boreserve boreserve);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Boreserve boreserve);

    /**
      * 根据ID查询
    */
    Boreserve selectById(Integer id);

    /**
      * 查询所有
    */
    List<Boreserve> selectAll(Boreserve boreserve);

}