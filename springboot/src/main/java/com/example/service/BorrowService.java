package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.entity.Account;
import com.example.entity.Borrow;
import com.example.mapper.BorrowMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教室器材预约前台表业务处理
 **/
@Service
public class BorrowService {

    @Resource
    private BorrowMapper borrowMapper;

    /**
     * 新增
     */
    public void add(Borrow borrow) {
        borrowMapper.insert(borrow);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        borrowMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            borrowMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Borrow borrow) {
        borrowMapper.updateById(borrow);
    }

    /**
     * 根据ID查询
     */
    public Borrow selectById(Integer id) {
        return borrowMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Borrow> selectAll(Borrow borrow) {
        return borrowMapper.selectAll(borrow);
    }

    /**
     * 分页查询
     */
    public PageInfo<Borrow> selectPage(Borrow borrow, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Borrow> list = borrowMapper.selectAll(borrow);
        return PageInfo.of(list);
    }

}