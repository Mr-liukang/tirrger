package com.liukang.tirrger.controller;

import com.liukang.tirrger.pojo.User;
import com.liukang.tirrger.service.MybatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mybatis")
public class MybatisController {
  @Autowired
   private  MybatisUserService mybatisUserService;

    @RequestMapping("/getuser")
    @ResponseBody
    public User getUser(Long id){
      return mybatisUserService.getUser(id);
  }
}
