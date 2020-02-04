package com.liukang.tirrger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/interceptor")
public class InterceptorController {
    @GetMapping("/start")
    public String start(){
        return "/welcome";
    }
}
