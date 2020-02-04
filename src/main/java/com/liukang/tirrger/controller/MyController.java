package com.liukang.tirrger.controller;

import com.liukang.tirrger.pojo.User;
import com.liukang.tirrger.pojo.ValidatorPojo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;


import java.util.List;
import java.util.Map;

@RequestMapping("/my")
@Controller
public class MyController  {
   @GetMapping("/no/annotation")
   @ResponseBody
    public Map<String,Object> noAnnotation(Integer intVal,Long longVal,String str){
       Map<String,Object> map = new HashMap<>();
       map.put("intVal",intVal);
       map.put("longVal",longVal);
       map.put("str",str);
       return map;

   }
    @GetMapping("/annotation")
    @ResponseBody
    public Map<String,Object> annotation
            (@RequestParam(value = "int_val",required = false) Integer intVal,
                                         @RequestParam(value = "long_val",required = false)Long longVal,
                                         @RequestParam(value = "str_val",required = false)String str){
        Map<String,Object> map = new HashMap<>();
        map.put("intVal",intVal);
        map.put("longVal",longVal);
        map.put("str",str);
        return map;

    }
    @GetMapping("/format/form")
    @ResponseBody
    public Map<String,Object> format(@RequestParam("sdate") Date date,
                         @NumberFormat(pattern = "#,###.##")Double number){
       Map<String,Object> map = new HashMap<>();
       map.put("date",date);
       map.put("number",number);
       return map;

    }
    @GetMapping("/converter")
    @ResponseBody
    public User getUserByConverter(User user){
       return user;
    }
    @GetMapping("/converter1")
    @ResponseBody
    public Map<String,Object> getUserByConverterq(User user,String str){
       Map<String,Object> map = new HashMap<>();
       map.put("user",user);
       map.put("str",str);
       return map;
    }
    @GetMapping("/list3")
    @ResponseBody
    public List<User> list(@RequestParam(value ="listUser")List<User> listUser){
       return listUser;

    }
    @GetMapping("/valid/page")
    public String validaPage(){
       return "validator/validator";
    }
    @RequestMapping("/valid/validate")
    @ResponseBody
    public  Map<String,Object> validator(@Valid ValidatorPojo pojo,
                                         Errors errors){
       Map<String,Object> errMap = new HashMap<>();
        List<ObjectError> allErrors = errors.getAllErrors();
        for(ObjectError er: allErrors){
            String key = "";
            String msg = "";
            if(er instanceof FieldError){
                FieldError fe = (FieldError) er;
                key =  fe.getField();
            }else{
                key = er.getObjectName();
            }
            msg =  er.getDefaultMessage();
            errMap.put(key,msg);
        }

        return errMap;
    }
}
