package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author chairuntao
 * @version 1.0
 * @date 2020/5/8 22:19
 */
@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadAvatar(MultipartFile file) {
        String endPoint = ConstantPropertiesUtils.END_POINT;
        String keyId = ConstantPropertiesUtils.KEY_ID;
        String keySecret = ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;


        try {
            final OSS build = new OSSClientBuilder().build(endPoint, keyId, keySecret);
            final InputStream inputStream = file.getInputStream();
            //第二个参数是  oss文件路径和文件名称
            //第三个参数是文件输入流
            build.putObject(bucketName, file.getOriginalFilename(), inputStream);

            build.shutdown();
            String url="";
//            https://crt-edu.oss-cn-shanghai.aliyuncs.com/20150919092957_J3NQT.jpeg
            url="https://"+bucketName+"."+endPoint+"/"+file.getOriginalFilename();
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
}
