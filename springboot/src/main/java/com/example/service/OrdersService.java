package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.entity.*;
import com.example.exception.CustomException;
import com.example.mapper.CourseMapper;
import com.example.mapper.OrdersMapper;
import com.example.mapper.TeacherMapper;
import com.example.mapper.UserMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * 课程商品订单表业务处理
 **/
@Service
public class OrdersService {

    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private TeacherMapper teacherMapper;

    /**
     * 新增
     */
    public void add(Orders orders) {
        //先判断用户余额够不够
        User user = userMapper.selectById(orders.getUserId());
        if(user.getAccount()<orders.getPrice()){
            throw new CustomException("-1","您的余额不足，请到个人中心充值");
        }
        orders.setTime(DateUtil.now());
        orders.setOrderNo(DateUtil.format(new Date(),"yyyyMMddHHmmss"));
        ordersMapper.insert(orders);
        //扣除用户的余额
        user.setAccount(user.getAccount()-orders.getPrice());
        userMapper.updateById(user);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        ordersMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            ordersMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Orders orders) {
        ordersMapper.updateById(orders);
    }

    /**
     * 根据ID查询
     */
    public Orders selectById(Integer id) {
        return ordersMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Orders> selectAll(Orders orders) {
        return ordersMapper.selectAll(orders);
    }

    /**
     * 分页查询
     */
    public PageInfo<Orders> selectPage(Orders orders, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Orders> list = ordersMapper.selectAll(orders);
        for(Orders dbOrders:list){
            Integer userId=dbOrders.getUserId();
            User user= userMapper.selectById(userId);
            if(ObjectUtil.isNotEmpty(user)){
                dbOrders.setUserName(user.getName());
            }
           Course course= courseMapper.selectById(dbOrders.getCourseId());
            if(ObjectUtil.isNotEmpty(course)){
                dbOrders.setCourseName(course.getName());
                Teacher teacher=teacherMapper.selectById(course.getTeacherId());
                if(ObjectUtil.isNotEmpty(teacher)){
                    dbOrders.setTeacherName(teacher.getName());
                    dbOrders.setTeacherId(teacher.getId());
                }
            }
        }
        return PageInfo.of(list);
    }

}