package com.dbz.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dbz.commonutils.Result;
import com.dbz.eduservice.entity.EduComment;
import com.dbz.eduservice.mapper.EduCommentMapper;
import com.dbz.eduservice.service.EduCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author dbz
 * @since 2022-04-17
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {

    @Override
    public Map<String, Object> pageQueryComment(Page<EduComment> pageComment, String courseId) {
        QueryWrapper<EduComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        baseMapper.selectPage(pageComment,queryWrapper);

        Map<String, Object> map = new HashMap<>();
        List<EduComment> commentList = pageComment.getRecords();
        map.put("items", commentList);
        map.put("current", pageComment.getCurrent());
        map.put("pages", pageComment.getPages());
        map.put("size", pageComment.getSize());
        map.put("total", pageComment.getTotal());
        map.put("hasNext", pageComment.hasNext());
        map.put("hasPrevious", pageComment.hasPrevious());
        return map;

    }
}
