package com.dbz.cmsservice.service;

import com.dbz.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author dbz
 * @since 2022-04-13
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> getAllBanner();
}
