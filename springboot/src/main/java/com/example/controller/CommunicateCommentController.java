package com.example.controller;

import com.example.common.Result;
import com.example.entity.CommunicateComment;
import com.example.service.CommunicateCommentService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公告信息表前端操作接口
 **/
@RestController
@RequestMapping("/communicateComment")
public class CommunicateCommentController {

    @Resource
    private CommunicateCommentService communicateCommentService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody CommunicateComment communicateComment) {
        communicateCommentService.add(communicateComment);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        communicateCommentService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        communicateCommentService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody CommunicateComment communicateComment) {
        communicateCommentService.updateById(communicateComment);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        CommunicateComment communicateComment = communicateCommentService.selectById(id);
        return Result.success(communicateComment);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(CommunicateComment communicateComment ) {
        List<CommunicateComment> list = communicateCommentService.selectAll(communicateComment);
        return Result.success(list);
    }

    /**
     * 查询某个论坛下的所有评论和回复
     */
    @GetMapping("/selectByCommunicateId/{communicateId}")
    public Result selectByCommunicateId(@PathVariable Integer communicateId) {
        List<CommunicateComment> list = communicateCommentService.selectByCommunicateId(communicateId);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(CommunicateComment communicateComment,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<CommunicateComment> page = communicateCommentService.selectPage(communicateComment, pageNum, pageSize);
        return Result.success(page);
    }

}