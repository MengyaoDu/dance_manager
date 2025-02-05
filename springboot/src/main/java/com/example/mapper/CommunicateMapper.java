package com.example.mapper;

import com.example.entity.Communicate;

import java.util.List;

/**
 * 操作communicate相关数据接口
*/
public interface CommunicateMapper {

    /**
      * 新增
    */
    int insert(Communicate communicate);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Communicate communicate);

    /**
      * 根据ID查询
    */
    Communicate selectById(Integer id);

    /**
      * 查询所有
    */
    List<Communicate> selectAll(Communicate communicate);

}