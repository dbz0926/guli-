package com.dbz.statisticservice.controller;


import com.dbz.commonutils.Result;
import com.dbz.statisticservice.client.UcenterClient;
import com.dbz.statisticservice.entity.StatisticsDaily;
import com.dbz.statisticservice.service.StatisticsDailyService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author dbz
 * @since 2022-04-19
 */
@RestController
@RequestMapping("/statisticservice/daily")
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService dailyService;

    /**
     * 添加注册人数记录
     * @param day
     * @return
     */
    @PostMapping("registerMemberCount/{day}")
    public Result registerMemberCountByDay(@PathVariable("day") String day){
        dailyService.registerMemberCountByDay(day);
        return Result.ok();
    }

    /**
     * 获取注册人数
     * @param begin
     * @param end
     * @param type
     * @return
     */
    @GetMapping("showchart/{begin}/{end}/{type}")
    public Result showChart(@PathVariable String begin,@PathVariable String end,@PathVariable String type){
        Map<String, Object> map = dailyService.getChartData(begin, end, type);
        return Result.ok().data(map);
    }
}

