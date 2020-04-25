package com.bwei.oracle_coundday0421.utils;

import cn.hutool.core.util.RandomUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @User feifei
 * @ClassName StaticFlag  类名
 * @Author Administrator  作者
 * @Date 2020-04-22 20:54  时间
 * @Version 1.0 版本
 */

public class StaticFlag {
    /**
     * session用户的key
     */
    public static final String USERUNFO="userinfo";

    /**
     * 获取userid
     * @return
     */
    public static String queryID(){
        return RandomUtil.randomUUID().replace("-","");
    }

    public static  String  uploadUtils(MultipartFile file , String filePath){
        //获取上传文件的名
        String filename = file.getOriginalFilename();
        //定义上传图片保存路径
        String path =  filePath +"rotPhoto/";
        //如果不存在则新建上传文件路径
        File filepath = new File(path, filename);
        if(!filepath.getParentFile().exists()){
            filepath.getParentFile().mkdirs();
        }
        //写入文件到指定目录   File.separator == /
        try {
            file.transferTo(new File(path+ File.separator+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将保存的文件的地址响应给html页面
        return   "/images/rotPhoto/"+filename ;

    }










}
