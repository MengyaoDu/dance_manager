package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.Borrow;
import com.example.entity.Course;
import com.example.entity.Orders;
import com.example.entity.Reserve;
import com.example.service.BorrowService;
import com.example.service.CourseService;
import com.example.service.OrdersService;
import com.example.service.ReserveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Resource
    private BorrowService borrowService;
    @Resource
    private OrdersService ordersService;
    @Resource
    private CourseService courseService;
    @Resource
    private ReserveService reserveService;
    @GetMapping("/borrowData")
    public Result borrrowData(){

        Map<String,Object> resultMap=new HashMap<>();
        List<Map<String,Object>> list=new ArrayList<>();

        List<Borrow> all=borrowService.selectAll(new Borrow());
        Map<String,Long> collect=all.stream().filter(x-> ObjectUtil.isNotEmpty(x.getStatus()))
                .collect(Collectors.groupingBy(Borrow::getStatus,Collectors.counting()));
        for(String key : collect.keySet()){
            Map<String,Object> map=new HashMap<>();
            map.put("name",key);
            map.put("value",collect.get(key));
            list.add(map);
        }

        resultMap.put("text","舞蹈室器材使用情况统计");
        resultMap.put("subtext","统计维度：使用状态");
        resultMap.put("name","数量");
        resultMap.put("data",list);
        return Result.success(resultMap);
    }

    @GetMapping("/courseData")
    public Result courseData(){

        Map<String,Object> resultMap=new HashMap<>();
        List<Map<String,Object>> list=new ArrayList<>();

        List<Orders> all=ordersService.selectAll(new Orders());
        for (Orders orders :all){
            Course course=courseService.selectById(orders.getCourseId());
            if(ObjectUtil.isNotEmpty(course)){
                orders.setCourseName(course.getName());
            }
        }
        Map<String,Double> collect=all.stream().filter(x-> ObjectUtil.isNotEmpty(x.getCourseName()))
                .collect(Collectors.groupingBy(Orders::getCourseName,Collectors.reducing(0.0,Orders::getPrice,Double::sum)));

        for(String key : collect.keySet()){
            Map<String,Object> map=new HashMap<>();
            map.put("name",key);
            map.put("value",collect.get(key));
            list.add(map);
        }

        resultMap.put("text","舞蹈室团课销售情况统计");
        resultMap.put("subtext","统计维度：售出课程");
        resultMap.put("name","金额");
        resultMap.put("data",list);
        return Result.success(resultMap);
    }

    @GetMapping("/teacherData")
    public Result teacherData(){

        Map<String,Object> resultMap=new HashMap<>();
        List<Object> xAxis=new ArrayList<>();
        List<Long> yAxis=new ArrayList<>();

        List<Reserve> all=reserveService.selectAll(new Reserve());
        Map<String,Long> collect=all.stream().filter(x-> ObjectUtil.isNotEmpty(x.getTeacherName()))
                        .collect(Collectors.groupingBy(Reserve::getTeacherName,Collectors.counting()));

        for (String key : collect.keySet()){
            xAxis.add(key);
            yAxis.add(collect.get(key));
        }

        resultMap.put("text","导师预约情况统计");
        resultMap.put("subtext","统计维度：导师姓名");
        resultMap.put("xAxis",xAxis);
        resultMap.put("yAxis",yAxis);
        return Result.success(resultMap);
    }

}
