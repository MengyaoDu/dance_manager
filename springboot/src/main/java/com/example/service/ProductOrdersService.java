package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Account;
import com.example.entity.Product;
import com.example.entity.ProductOrders;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.ProductMapper;
import com.example.mapper.ProductOrdersMapper;
import com.example.mapper.UserMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 公告信息表业务处理
 **/
@Service
public class ProductOrdersService {

    @Resource
    private ProductOrdersMapper productOrdersMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ProductMapper productMapper;
    /**
     * 新增
     */
    public void add(ProductOrders productOrders) {
        //判断用户余额
        User user=userMapper.selectById(productOrders.getUserId());
        if(user.getAccount()<productOrders.getPrice()){
            throw new CustomException("-1","您的余额不足，请到个人中心充值");
        }
        productOrders.setTime(DateUtil.now());
        productOrders.setOrderNo(DateUtil.format(new Date(),"yyyyMMddHHmmss"));
        productOrdersMapper.insert(productOrders);
        //扣除用户的余额
        user.setAccount(user.getAccount()-productOrders.getPrice());
        userMapper.updateById(user);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        productOrdersMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            productOrdersMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(ProductOrders productOrders) {
        productOrdersMapper.updateById(productOrders);
    }

    /**
     * 根据ID查询
     */
    public ProductOrders selectById(Integer id) {
        return productOrdersMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<ProductOrders> selectAll(ProductOrders productOrders) {
        return productOrdersMapper.selectAll(productOrders);
    }

    /**
     * 分页查询
     */
    public PageInfo<ProductOrders> selectPage(ProductOrders productOrders, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductOrders> list = productOrdersMapper.selectAll(productOrders);
        for(ProductOrders dbproductOrders:list){
            Integer userId=dbproductOrders.getUserId();
            User user=userMapper.selectById(userId);
            if(ObjectUtil.isNotEmpty(user)){
                dbproductOrders.setUserName(user.getName());
            }
            Product product=productMapper.selectById(dbproductOrders.getProductId());
            if(ObjectUtil.isNotEmpty(product)){
                dbproductOrders.setProductName(product.getName());
            }
        }
        return PageInfo.of(list);
    }

}