package com.dbz.cmsservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dbz.cmsservice.entity.CrmBanner;
import com.dbz.cmsservice.service.CrmBannerService;
import com.dbz.commonutils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author dbz
 * @since 2022-04-13
 */
@RestController
@RequestMapping("/cmsservice/banner")
public class BannerFrontController {
    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("getAllBanner")
    public Result getAllBanner(){
        List<CrmBanner> list = crmBannerService.getAllBanner();
        return Result.ok().data("bannerList",list);
    }
}

