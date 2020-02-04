package com.liukang.tirrger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileController {
   @GetMapping("/upload/page")
   public String uploadPage(){
       return "file/upload";
   }
   @PostMapping("/upload/request")
    @ResponseBody
    public Map<String,Object> uploadRequest(HttpServletRequest request){
       boolean flag = false;
       MultipartHttpServletRequest mreq = null;
       if(request instanceof MultipartHttpServletRequest){
           mreq = (MultipartHttpServletRequest)request;
       }else{
           dealResultMap(flag,"上传文件失败");
       }
       MultipartFile mf = mreq.getFile("file");
       String fileName = mf.getOriginalFilename();
       File file = new File(fileName);
       try {
           mf.transferTo(file);
       }catch (Exception e){
           return dealResultMap(false,"上传失败");
       }

       return  dealResultMap(true,"上传成功");

   }
   private Map<String,Object> dealResultMap(boolean success,String msg){
       Map<String,Object> result = new HashMap<>();
       result.put("success",success);
       result.put("msg",msg);
       return result;
   }
}
