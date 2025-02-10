package com.example.mapper;

import com.example.entity.Product;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作product相关数据接口
*/
public interface ProductMapper {

    /**
      * 新增
    */
    int insert(Product product);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Product product);

    /**
      * 根据ID查询
    */
    Product selectById(Integer id);

    /**
      * 查询所有
    */
    List<Product> selectAll(Product product);

    @Select("select * from product order by id desc limit 4")
    List<Product> selectFour();
}