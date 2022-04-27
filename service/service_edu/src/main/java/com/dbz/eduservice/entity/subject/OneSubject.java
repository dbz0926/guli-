package com.dbz.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 10979
 * @description:
 * @date 2022/4/422:08
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    private List<TwoSubject> children = new ArrayList<>();
}
