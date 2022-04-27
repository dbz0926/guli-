package com.dbz.statisticservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dbz.commonutils.JwtUtils;
import com.dbz.commonutils.Result;
import com.dbz.statisticservice.entity.TOrder;
import com.dbz.statisticservice.service.TOrderService;
import com.dbz.servicebase.handler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author dbz
 * @since 2022-04-17
 */
@RestController
@RequestMapping("/orderservice/order")
public class TOrderController {
    @Autowired
    private TOrderService orderService;

    @PostMapping("createOrder/{crouseId}")
    public Result createOrderByCourseId(@PathVariable String crouseId, HttpServletRequest request){
        String memberId=JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)){
            throw  new GuliException(20001,"用户未登录,请登录后再购买");
        }
        String orderNo = orderService.createOrderByCourseId(crouseId, memberId);
        return Result.ok().data("orderId",orderNo);
    }

    @GetMapping("getOrder/{orderNo}")
    public Result get(@PathVariable String orderNo) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        TOrder order = orderService.getOne(wrapper);
        return Result.ok().data("item", order);
    }

    @GetMapping("isBuyCourse/{memberid}/{courseId}")
    public boolean isBuyCourse(@PathVariable String memberid,
                               @PathVariable String courseId) {
        //订单状态是1表示支付成功
        int count = orderService.count(new QueryWrapper<TOrder>().eq("member_id", memberid).eq("course_id", courseId).eq("status", 1));
        return count > 0;
    }
}

