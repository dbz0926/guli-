package com.dbz.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.dbz.oss.service.OssService;
import com.dbz.oss.utils.ConstantPropertiesUtil;
import com.dbz.servicebase.handler.GuliException;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author 10979
 * @description:
 * @date 2022/4/415:22
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        String uuid = UUID.randomUUID().toString();
        String filename=file.getOriginalFilename();
        String time = new DateTime().toString("yyyy/MM/dd");
        String newFilename=time+"/"+uuid+filename.substring(filename.lastIndexOf("."));
        
        try {
            InputStream inputStream = file.getInputStream();
            // 创建PutObject请求。
            ossClient.putObject(bucketName, newFilename, inputStream);
        } catch (IOException oe) {
            oe.printStackTrace();
        }  finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        String uploadUrl = "http://" + bucketName + "." + endpoint + "/" + newFilename;
        return uploadUrl;
    }
}
