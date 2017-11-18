/*
package com.qf.upload;

import com.qf.ddshop.common.util.FtpUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FtpUploadTest {


    //加了@Test的这个方法，dd-parent每次运行时都会跑，所以确认无误，可以注释掉
    @Test
    public void testFtpUpload() throws IOException {
        //创建FTPClient客户端
        FTPClient ftpClient = new FTPClient();
        //创建FTP连接
        ftpClient.connect("10.31.161.48", 21);
        //登录
        ftpClient.login("ftpuser", "dhc890dhc");
        //读取本地文件
        FileInputStream fileInputStream = new FileInputStream(new File("d:\\123.jpg"));
        //配置上传参数
        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //上传文件,hello.jpg为上传的名称
        ftpClient.storeFile("hello.jpg", fileInputStream);
        //关闭连接
        fileInputStream.close();
        ftpClient.logout();

    }

    @Test
    public void testFtpUtil() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("d:\\123.jpg"));
        FtpUtils.uploadFile("10.31.161.48", 21, "ftpuser", "dhc890dhc", "/home/ftpuser/www/images", "/2017/11/16", "hello2.jpg", fileInputStream);
        fileInputStream.close();
    }


}
*/
