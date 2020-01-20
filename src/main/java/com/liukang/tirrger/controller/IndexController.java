package com.liukang.tirrger.controller;

import com.liukang.tirrger.pojo.DataBaseProperties;
import com.liukang.tirrger.pojo.User;
import com.liukang.tirrger.service.JdbcTmplUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @Autowired
    DataBaseProperties dataBaseProperties;
    @Autowired
    JdbcTmplUserService jdbcTmplUserService;
    @RequestMapping("/index")
    public String index(){
        System.out.println(dataBaseProperties.getDriverName());
        return "index";
    }
    @RequestMapping("/user")
    @ResponseBody
    public User getUser(Long id){

        User user = jdbcTmplUserService.getUser(id);
        return user;
    }
}
