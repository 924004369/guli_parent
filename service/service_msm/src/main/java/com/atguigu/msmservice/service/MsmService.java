package com.atguigu.msmservice.service;

import java.util.Map;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/24 9:23
 */
public interface MsmService {
    Boolean send(Map<String, Object> map, String phone);
}
