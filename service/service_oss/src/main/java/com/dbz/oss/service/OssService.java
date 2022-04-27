package com.dbz.oss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 10979
 * @description:
 * @date 2022/4/415:22
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
