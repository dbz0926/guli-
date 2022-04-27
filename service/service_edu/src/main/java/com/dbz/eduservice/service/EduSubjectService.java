package com.dbz.eduservice.service;

import com.dbz.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dbz.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author dbz
 * @since 2022-04-04
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService subjectService);

    List<OneSubject> getAllOneAndTwoSubject();
}
