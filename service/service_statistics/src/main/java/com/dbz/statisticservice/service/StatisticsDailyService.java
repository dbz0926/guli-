package com.dbz.statisticservice.service;

import com.dbz.statisticservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author dbz
 * @since 2022-04-19
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void registerMemberCountByDay(String day);

    Map<String, Object> getChartData(String begin, String end, String type);
}
