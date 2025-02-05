package com.example.controller;

import com.example.common.Result;
import com.example.entity.Communicate;
import com.example.service.CommunicateService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 交流信息表前端操作接口
 **/
@RestController
@RequestMapping("/communicate")
public class CommunicateController {

    @Resource
    private CommunicateService communicateService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Communicate communicate) {
        communicateService.add(communicate);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        communicateService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        communicateService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Communicate communicate) {
        communicateService.updateById(communicate);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Communicate communicate = communicateService.selectById(id);
        return Result.success(communicate);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Communicate communicate ) {
        List<Communicate> list = communicateService.selectAll(communicate);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Communicate communicate,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Communicate> page = communicateService.selectPage(communicate, pageNum, pageSize);
        return Result.success(page);
    }

}