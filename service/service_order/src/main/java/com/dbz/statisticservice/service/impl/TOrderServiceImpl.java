package com.dbz.statisticservice.service.impl;

import com.dbz.commonutils.vo.CourseWebOrder;
import com.dbz.commonutils.vo.UcenterMemberOrder;
import com.dbz.statisticservice.entity.TOrder;
import com.dbz.statisticservice.feign.OrderFeignCourse;
import com.dbz.statisticservice.feign.OrderFeignMember;
import com.dbz.statisticservice.mapper.TOrderMapper;
import com.dbz.statisticservice.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dbz.statisticservice.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author dbz
 * @since 2022-04-17
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
    @Autowired
    private OrderFeignCourse orderFeignCourse;

    @Autowired
    private OrderFeignMember orderFeignMember;
    /**
     * 生成订单
     * @param crouseId
     * @param memberIdByJwtToken
     * @return
     */
    @Override
    public String createOrderByCourseId(String crouseId, String memberIdByJwtToken) {
        //调用根据用户id获取用户信息的模块
        UcenterMemberOrder ucenterMemberOrder = orderFeignMember.getMemberInfoOrderByMemberId(memberIdByJwtToken);
        //调用根据课程id获取课程信息的模块
        CourseWebOrder courseInfoOrder = orderFeignCourse.getCourseInfoOrder(crouseId);
        //创建订单
        TOrder order = new TOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(crouseId);
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName("test");
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberIdByJwtToken);
        order.setMobile(ucenterMemberOrder.getMobile());
        order.setNickname(ucenterMemberOrder.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
