package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/8 22:18
 */
public interface OssService {
    String uploadAvatar(MultipartFile file);
}
