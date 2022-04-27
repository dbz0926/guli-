package com.dbz.cmsservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dbz.cmsservice.entity.CrmBanner;
import com.dbz.cmsservice.service.CrmBannerService;
import com.dbz.commonutils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 10979
 * @description:后台管理banner
 * @date 2022/4/1317:04
 */
@RestController
@RequestMapping("/eduservice/banner")
public class BannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("pageBanner/{curPage}/{limit}")
    public Result pageBanner(@PathVariable("curPage") Long curPage,@PathVariable("limit") Long limit){
        Page<CrmBanner> crmBannerPage = new Page<>(curPage, limit);
        IPage<CrmBanner> page = crmBannerService.page(crmBannerPage, null);
        List<CrmBanner> list = page.getRecords();
        long total = page.getTotal();
        return Result.ok().data("items",list).data("total",total);
    }

    @PostMapping("saveBanner")
    public Result saveBanner(@RequestBody CrmBanner crmBanner){
        boolean save = crmBannerService.save(crmBanner);
        if (save) return Result.ok();
        return Result.error();
    }

    @PutMapping("updateBanner")
    public Result updateBanner(@RequestBody CrmBanner crmBanner){
        boolean b = crmBannerService.updateById(crmBanner);
        if (b){
            return  Result.ok();
        }
        return Result.error();
    }

    @DeleteMapping("removeBanner/{id}")
    public Result removeBannerById(@PathVariable(value = "id") String id){
        boolean b = crmBannerService.removeById(id);
        if (b){
            return  Result.ok();
        }
        return Result.error();
    }

    @GetMapping("getBannerByid/{id}")
    public Result getBannerByid(@PathVariable String id){
        CrmBanner crmBanner = crmBannerService.getById(id);
        return Result.ok().data("item",crmBanner);
    }
}
