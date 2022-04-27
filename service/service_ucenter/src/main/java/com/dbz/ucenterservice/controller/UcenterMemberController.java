package com.dbz.ucenterservice.controller;


import com.dbz.commonutils.JwtUtils;
import com.dbz.commonutils.Result;
import com.dbz.commonutils.vo.UcenterMemberOrder;
import com.dbz.ucenterservice.entity.UcenterMember;
import com.dbz.ucenterservice.entity.vo.RegisterVo;
import com.dbz.ucenterservice.service.UcenterMemberService;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author dbz
 * @since 2022-04-14
 */
@RestController
@RequestMapping("/ucenterservice/member")
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;

    @PostMapping("login")
    public Result loginUser(@RequestBody(required = false) UcenterMember ucenterMember){
        String token = memberService.login(ucenterMember);
        System.out.println(token);
        return Result.ok().data("token",token);
    }

    @PostMapping("register")
    public Result register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return Result.ok();
    }

    @GetMapping("getUcenterMemberByToken")
    public Result getUcenterMemberByToken(HttpServletRequest request){
        String id = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = memberService.getById(id);
        System.out.println(member.toString());
        return Result.ok().data("item",member);
    }

    @GetMapping("getMemberInfo/{memberId}")
    public UcenterMember getMemberInfoByMemberId(@PathVariable String memberId){
        UcenterMember ucenterMember = memberService.getById(memberId);
        return ucenterMember;
    }

    @GetMapping("getMemberInfoOrder/{memberId}")
    public UcenterMemberOrder getMemberInfoOrderByMemberId(@PathVariable String memberId){
        UcenterMember ucenterMember = memberService.getById(memberId);
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(ucenterMember,ucenterMemberOrder);
        return ucenterMemberOrder;
    }

    @GetMapping("countRegister/{day}")
    public Result countRegisterByDay(@PathVariable String day){
        int count = memberService.countRegisterByDay(day);
        return Result.ok().data("countRegister",count);
    }
}

