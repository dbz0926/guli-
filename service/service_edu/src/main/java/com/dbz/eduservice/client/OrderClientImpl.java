package com.dbz.eduservice.client;

import org.springframework.stereotype.Component;

/**
 * @author 10979
 * @description:
 * @date 2022/4/1919:57
 */
@Component
public class OrderClientImpl implements OrderClient{
    @Override
    public boolean isBuyCourse(String memberid, String courseId) {
        return false;
    }
}
