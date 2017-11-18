package com.qf.ddshop.web;

import com.qf.ddshop.service.FileService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

@Controller
public class FileAction {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

     @Autowired
    private FileService fileService;

  @ResponseBody
  @RequestMapping(value = "file/upload",method = RequestMethod.GET)
    public  void config(HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String action=request.getParameter("action");
        if ("config".equals(action)){
            PrintWriter out = response.getWriter();
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("config.json");
            IOUtils.copy(is,out,"UTF-8");


        }


    }
    @ResponseBody
    @RequestMapping(value = "/file/upload",method=RequestMethod.POST)
//    multipartFile这个取文件属性
    public Map<String,Object> upload(MultipartFile upfile){
      Map<String,Object> map=null;
      try{
          map=fileService.uploadImages(upfile);

      }catch (Exception e){
          logger.error(e.getMessage(),e);
          e.printStackTrace();
      }
        return map;
    }
}
