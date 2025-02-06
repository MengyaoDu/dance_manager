package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Communicate;
import com.example.entity.CommunicateComment;
import com.example.entity.User;
import com.example.mapper.CommunicateCommentMapper;
import com.example.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 论坛评论信息表业务处理
 **/
@Service
public class CommunicateCommentService {

    @Resource
    private CommunicateCommentMapper communicateCommentMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 新增
     */
    public void add(CommunicateComment communicateComment) {
        communicateComment.setTime(DateUtil.now());
        communicateCommentMapper.insert(communicateComment);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        communicateCommentMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            communicateCommentMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(CommunicateComment communicateComment) {
        communicateCommentMapper.updateById(communicateComment);
    }

    /**
     * 根据ID查询
     */
    public CommunicateComment selectById(Integer id) {
        return communicateCommentMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<CommunicateComment> selectAll(CommunicateComment communicateComment) {
        List<CommunicateComment> list= communicateCommentMapper.selectAll(communicateComment);
        for(CommunicateComment dbComment:list){
            User user=userMapper.selectById(dbComment.getUserId());
            if(ObjectUtil.isNotEmpty(user)){
                dbComment.setUserName(user.getName());
                dbComment.setUserAvatar(user.getAvatar());
            }
        }
        return list;
    }

    /**
     * 查询某个论坛下的所有评论和回复
     */
    public List<CommunicateComment> selectByCommunicateId(Integer communicateId) {
        List<CommunicateComment> list = communicateCommentMapper.selectByCommunicateId(communicateId);
        for (CommunicateComment dbComment : list) {
            User user = userMapper.selectById(dbComment.getUserId());
            if (ObjectUtil.isNotEmpty(user)) {
                dbComment.setUserName(user.getName());
                dbComment.setUserAvatar(user.getAvatar());
            }
        }
        return list;
    }

    /**
     * 分页查询
     */
    public PageInfo<CommunicateComment> selectPage(CommunicateComment communicateComment, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommunicateComment> list = communicateCommentMapper.selectAll(communicateComment);
        return PageInfo.of(list);
    }

}