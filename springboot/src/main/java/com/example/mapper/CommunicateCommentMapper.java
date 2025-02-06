package com.example.mapper;

import com.example.entity.CommunicateComment;

import java.util.List;

/**
 * 操作CommunicateComment相关数据接口
*/
public interface CommunicateCommentMapper {

    /**
      * 新增
    */
    int insert(CommunicateComment communicateComment);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(CommunicateComment communicateComment);

    /**
      * 根据ID查询
    */
    CommunicateComment selectById(Integer id);

    /**
      * 查询所有
    */
    List<CommunicateComment> selectAll(CommunicateComment communicateComment);

    /**
     * 查询某个论坛下的所有评论和回复
     */
    List<CommunicateComment> selectByCommunicateId(Integer communicateId);
}