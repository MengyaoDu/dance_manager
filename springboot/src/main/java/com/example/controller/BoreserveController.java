package com.example.controller;

import com.example.common.Result;
import com.example.entity.Boreserve;
import com.example.service.BoreserveService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公告信息表前端操作接口
 **/
@RestController
@RequestMapping("/boreserve")
public class BoreserveController {

    @Resource
    private BoreserveService boreserveService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Boreserve boreserve) {
        boreserveService.add(boreserve);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        boreserveService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        boreserveService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Boreserve boreserve) {
        boreserveService.updateById(boreserve);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Boreserve boreserve = boreserveService.selectById(id);
        return Result.success(boreserve);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Boreserve boreserve ) {
        List<Boreserve> list = boreserveService.selectAll(boreserve);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Boreserve boreserve,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Boreserve> page = boreserveService.selectPage(boreserve, pageNum, pageSize);
        return Result.success(page);
    }

}