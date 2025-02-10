package com.example.mapper;

import com.example.entity.ProductOrders;

import java.util.List;

/**
 * 操作productOrders相关数据接口
*/
public interface ProductOrdersMapper {

    /**
      * 新增
    */
    int insert(ProductOrders productOrders);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(ProductOrders productOrders);

    /**
      * 根据ID查询
    */
    ProductOrders selectById(Integer id);

    /**
      * 查询所有
    */
    List<ProductOrders> selectAll(ProductOrders productOrders);


}