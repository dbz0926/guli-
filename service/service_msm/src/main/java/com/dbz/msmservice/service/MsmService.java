package com.dbz.msmservice.service;

import java.util.Map;

/**
 * @author 10979
 * @description:
 * @date 2022/4/1416:23
 */
public interface MsmService {
    boolean send(String phone, String templateCode, Map<String, Object> param);
}
