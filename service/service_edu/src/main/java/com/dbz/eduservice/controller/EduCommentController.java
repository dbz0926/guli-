package com.dbz.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dbz.commonutils.JwtUtils;
import com.dbz.commonutils.Result;
import com.dbz.eduservice.entity.EduComment;
import com.dbz.eduservice.service.EduCommentService;
import com.dbz.eduservice.ucenter.UcenterMemberInfo;
import com.dbz.ucenterservice.entity.UcenterMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author dbz
 * @since 2022-04-17
 */
@RestController
@RequestMapping("/eduservice/comment")
public class EduCommentController {
    @Autowired
    private EduCommentService commentService;

    @Autowired
    private UcenterMemberInfo ucenterMemberInfo;

    @GetMapping("pagequery/{curpage}/{limit}")
    public Result pageQueryComment(@PathVariable Long curpage,
                                   @PathVariable Long limit,
                                   @RequestParam String courseId){
        Page<EduComment> pageComment = new Page<>(curpage, limit);
        Map<String,Object> map=commentService.pageQueryComment(pageComment,courseId);
        return Result.ok().data(map);
    }

    @PostMapping("saveComment")
    public Result saveComment(@RequestBody EduComment eduComment, HttpServletRequest request){

        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)){
            return Result.error().message("请先登录账号");
        }
        UcenterMember ucenterMember = ucenterMemberInfo.getMemberInfoByMemberId(memberId);
        eduComment.setMemberId(ucenterMember.getId());
        eduComment.setNickname(ucenterMember.getNickname());
        eduComment.setAvatar(ucenterMember.getAvatar());
        commentService.save(eduComment);
        return Result.ok();
    }
}

