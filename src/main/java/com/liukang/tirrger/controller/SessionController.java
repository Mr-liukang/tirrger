package com.liukang.tirrger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(names = {"user","users"},types = Long.class)
public class SessionController {

}
