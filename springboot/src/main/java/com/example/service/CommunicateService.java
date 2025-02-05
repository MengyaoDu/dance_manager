package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Communicate;
import com.example.entity.Teacher;
import com.example.entity.User;
import com.example.mapper.CommunicateMapper;
import com.example.mapper.TeacherMapper;
import com.example.mapper.UserMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 交流信息表业务处理
 **/
@Service
public class CommunicateService {

    @Resource
    private CommunicateMapper communicateMapper;
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 新增
     */
    public void add(Communicate communicate) {
        communicate.setTime(DateUtil.now()                                         );
        communicateMapper.insert(communicate);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        communicateMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            communicateMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Communicate communicate) {
        communicateMapper.updateById(communicate);
    }

    /**
     * 根据ID查询
     */
    public Communicate selectById(Integer id) {
        Communicate communicate = communicateMapper.selectById(id);
        if(RoleEnum.TEACHER.name().equals(communicate.getRole())){
            Teacher teacher =teacherMapper.selectById(communicate.getUserId());
            communicate.setUserName(teacher.getName());
        } else {
            User user = userMapper.selectById(communicate.getUserId());
            communicate.setUserName(user.getName());
        }
        return communicate;
    }

    /**
     * 查询所有
     */
    public List<Communicate> selectAll(Communicate communicate) {
        return communicateMapper.selectAll(communicate);
    }

    /**
     * 分页查询
     */
    public PageInfo<Communicate> selectPage(Communicate communicate, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Communicate> list = communicateMapper.selectAll(communicate);
        for (Communicate dbCommunicate: list){
            if(RoleEnum.TEACHER.name().equals(dbCommunicate.getRole())){
                Teacher teacher =teacherMapper.selectById(dbCommunicate.getUserId());
                dbCommunicate.setUserName(teacher.getName());
            } else {
                User user = userMapper.selectById(dbCommunicate.getUserId());
                dbCommunicate.setUserName(user.getName());
            }
        }
        return PageInfo.of(list);
    }

}