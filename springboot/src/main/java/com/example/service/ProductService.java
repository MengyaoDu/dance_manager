package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.entity.Account;
import com.example.entity.Product;
import com.example.mapper.ProductMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 舞室周边产品信息表业务处理
 **/
@Service
public class ProductService {

    @Resource
    private ProductMapper productMapper;

    /**
     * 新增
     */
    public void add(Product product) {
        productMapper.insert(product);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        productMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            productMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Product product) {
        productMapper.updateById(product);
    }

    /**
     * 根据ID查询
     */
    public Product selectById(Integer id) {
        return productMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Product> selectAll(Product product) {
        return productMapper.selectAll(product);
    }

    /**
     * 分页查询
     */
    public PageInfo<Product> selectPage(Product product, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> list = productMapper.selectAll(product);
        return PageInfo.of(list);
    }

    public List<Product> selectFour() {
        return productMapper.selectFour();
    }

}