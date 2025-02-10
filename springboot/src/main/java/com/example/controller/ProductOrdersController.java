package com.example.controller;

import com.example.common.Result;
import com.example.entity.ProductOrders;
import com.example.service.ProductOrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 产品订单信息表前端操作接口
 **/
@RestController
@RequestMapping("/productOrders")
public class ProductOrdersController {

    @Resource
    private ProductOrdersService productOrdersService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody ProductOrders productOrders) {
        productOrdersService.add(productOrders);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        productOrdersService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        productOrdersService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody ProductOrders productOrders) {
        productOrdersService.updateById(productOrders);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        ProductOrders productOrders = productOrdersService.selectById(id);
        return Result.success(productOrders);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(ProductOrders productOrders ) {
        List<ProductOrders> list = productOrdersService.selectAll(productOrders);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(ProductOrders productOrders,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<ProductOrders> page = productOrdersService.selectPage(productOrders, pageNum, pageSize);
        return Result.success(page);
    }

}