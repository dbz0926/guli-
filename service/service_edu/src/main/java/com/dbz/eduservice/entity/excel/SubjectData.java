package com.dbz.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author 10979
 * @description:
 * @date 2022/4/420:26
 */
@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
