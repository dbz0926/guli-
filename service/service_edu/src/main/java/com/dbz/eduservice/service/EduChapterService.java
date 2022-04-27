package com.dbz.eduservice.service;

import com.dbz.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dbz.eduservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author dbz
 * @since 2022-04-05
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean removeChapterById(String id);
}
