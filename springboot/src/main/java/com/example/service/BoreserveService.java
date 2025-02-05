package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.entity.Account;
import com.example.entity.Boreserve;
import com.example.mapper.BoreserveMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教室器材预约后台表业务处理
 **/
@Service
public class BoreserveService {

    @Resource
    private BoreserveMapper boreserveMapper;

    /**
     * 新增
     */
    public void add(Boreserve boreserve) {
        boreserveMapper.insert(boreserve);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        boreserveMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            boreserveMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Boreserve boreserve) {
        boreserveMapper.updateById(boreserve);
    }

    /**
     * 根据ID查询
     */
    public Boreserve selectById(Integer id) {
        return boreserveMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Boreserve> selectAll(Boreserve boreserve) {
        return boreserveMapper.selectAll(boreserve);
    }

    /**
     * 分页查询
     */
    public PageInfo<Boreserve> selectPage(Boreserve boreserve, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Boreserve> list = boreserveMapper.selectAll(boreserve);
        return PageInfo.of(list);
    }

}