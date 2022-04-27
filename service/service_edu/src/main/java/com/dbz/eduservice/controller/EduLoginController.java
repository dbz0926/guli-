package com.dbz.eduservice.controller;

import com.dbz.commonutils.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author 10979
 * @description:
 * @date 2022/4/315:04
 */
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {

    @PostMapping("login")
    public Result login(){
        return Result.ok().data("token","admin");
    }

    @GetMapping("info")
    public Result info(){
        return Result.ok().data("roles","[admin]").data("name","admin").data("avatar","http://5b0988e595225.cdn.sohucs.com/images/20171013/6f99180c52ad4aec85e50258a58a28fa.png");
    }
}
