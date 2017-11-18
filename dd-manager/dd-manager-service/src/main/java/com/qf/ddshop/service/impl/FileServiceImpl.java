package com.qf.ddshop.service.impl;

import com.qf.ddshop.common.util.FtpUtils;
import com.qf.ddshop.common.util.IDUtils;
import com.qf.ddshop.common.util.PropKit;
import com.qf.ddshop.service.FileService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

    private Logger logger= LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    private

    @Override
    public Map<String, Object> uploadImages(MultipartFile multipartFile) {
        Map<String,Object> map=new HashMap<String,Object>();

        //读取FTP配置文件信息
        String name ="ftp.properties";
        String host= PropKit.use(name).get("ftp.address");
        int port = PropKit.use(name).getInt("ftp.port");
        String username = PropKit.use(name).get("ftp.username");
        String password =PropKit.use(name).get("ftp.password");
        String basePath = PropKit.use(name).get("ftp.basePath");

         //创建文件路径：基础路径+文件路径+文件名+扩展名
         String filePath = new DateTime().toString("/yyyy/MM/dd");
         //获取原来文件的名字 ，包含扩展名
        String originalFilename =multipartFile.getOriginalFilename();
        //获取扩展名
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        //新文件名称
        String newname = IDUtils.genImageName() + fileType;

        InputStream inputStream=null;
        try {
             inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }



        boolean bool= FtpUtils.uploadFile(host,port,username,password,basePath,filePath,newname,inputStream);
        System.out.println("============================="+bool);
        if(bool){
            map.clear();
            map.put("state","SUCCESS");
            map.put("title",newname);
            map.put("original",originalFilename);
            map.put("type",fileType);
            map.put("url",filePath+"/"+newname);
            map.put("size",multipartFile.getSize());


            System.out.println("newname");
            System.out.println(newname);
        }
        return map;
    }
}
