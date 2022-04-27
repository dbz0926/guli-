package com.dbz.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dbz.eduservice.entity.EduChapter;
import com.dbz.eduservice.entity.EduVideo;
import com.dbz.eduservice.entity.vo.ChapterVo;
import com.dbz.eduservice.entity.vo.VideoVo;
import com.dbz.eduservice.mapper.EduChapterMapper;
import com.dbz.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dbz.eduservice.service.EduVideoService;
import com.dbz.servicebase.handler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author dbz
 * @since 2022-04-05
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        QueryWrapper<EduChapter> queryWrapperChapter = new QueryWrapper<>();
        queryWrapperChapter.eq("course_id", courseId);
        queryWrapperChapter.orderByDesc("gmt_create");
        List<EduChapter> eduChapterList = baseMapper.selectList(queryWrapperChapter);

        QueryWrapper<EduVideo> queryWrapperVideo = new QueryWrapper<>();
        queryWrapperVideo.eq("course_id", courseId);
        queryWrapperChapter.orderByDesc("gmt_create");
        List<EduVideo> eduVideoList = eduVideoService.list(queryWrapperVideo);

        List<ChapterVo> finalList=new ArrayList<>();
        Map<String,ChapterVo> map=new HashMap<>();
        for(EduChapter eduChapter:eduChapterList){
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            map.put(chapterVo.getId(),chapterVo);
            finalList.add(chapterVo);
        }
        for (EduVideo eduVideo:eduVideoList){
            String chapterId = eduVideo.getChapterId();
            ChapterVo chapterVo = map.get(chapterId);
            VideoVo videoVo = new VideoVo();
            BeanUtils.copyProperties(eduVideo,videoVo);
            chapterVo.getChildren().add(videoVo);
        }
        return finalList;
    }

    @Override
    public  boolean removeChapterById(String id) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper();
        queryWrapper.eq("chapter_id",id);
        int count = eduVideoService.count(queryWrapper);
        if (count>0){
            throw new GuliException(20001,"有子节，不能删除");
        }else {
            int i = baseMapper.deleteById(id);
            if (i>0) return true;
            return false;
        }
    }
}
