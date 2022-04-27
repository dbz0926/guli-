package com.dbz.statisticservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dbz.commonutils.Result;
import com.dbz.statisticservice.client.UcenterClient;
import com.dbz.statisticservice.entity.StatisticsDaily;
import com.dbz.statisticservice.mapper.StatisticsDailyMapper;
import com.dbz.statisticservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author dbz
 * @since 2022-04-19
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    @Autowired
    private UcenterClient ucenterClient;
    @Override
    public void registerMemberCountByDay(String day) {
        //先删除已有数据
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("date_calculated",day);
        baseMapper.delete(queryWrapper);
        //再添加新的数据
        Result registerByDay = ucenterClient.countRegisterByDay(day);
        Integer countRegister = (Integer) registerByDay.getData().get("countRegister");
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setRegisterNum(countRegister);
        statisticsDaily.setDateCalculated(day);
        statisticsDaily.setVideoViewNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setLoginNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setCourseNum(RandomUtils.nextInt(100,200));
        baseMapper.insert(statisticsDaily);
    }

    @Override
    public Map<String, Object> getChartData(String begin, String end, String type) {
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("date_calculated",begin,end);
        queryWrapper.select("date_calculated",type);
        List<StatisticsDaily> statisticsDailyList = baseMapper.selectList(queryWrapper);

        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<Integer> nums = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("nums", nums);
        map.put("dateList", dateList);

        for (int i = 0; i < statisticsDailyList.size(); i++) {
            StatisticsDaily daily = statisticsDailyList.get(i);
            dateList.add(daily.getDateCalculated());
            switch (type) {
                case "register_num":
                    nums.add(daily.getRegisterNum());
                    break;
                case "login_num":
                    nums.add(daily.getLoginNum());
                    break;
                case "video_view_num":
                    nums.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    nums.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        return map;
    }
}
