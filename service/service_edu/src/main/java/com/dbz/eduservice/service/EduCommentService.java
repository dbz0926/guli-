package com.dbz.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dbz.eduservice.entity.EduComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author dbz
 * @since 2022-04-17
 */
public interface EduCommentService extends IService<EduComment> {

    Map<String, Object> pageQueryComment(Page<EduComment> pageComment, String courseId);
}
