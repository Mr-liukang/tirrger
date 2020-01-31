package com.liukang.tirrger.controller;


import com.liukang.tirrger.pojo.User;

import com.liukang.tirrger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
   @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id){
       return userService.getUser(id);
    }
    @RequestMapping("/insertUser")
    @ResponseBody
    public User insertUser(Long id,String userName,String note){
        User user = new User();
        user.setNote(note);
        user.setUserName(userName);
        user.setId(id);
        return userService.insertUser(user);
    }
    @RequestMapping("details")
    public ModelAndView details(Long id){
        User user = userService.getUser(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/details");
        mv.addObject("user",user);
        return mv;
    }
    @RequestMapping("table")
    public ModelAndView table(){
        List<User> users = userService.findUsers(null,null);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/table");
        mv.addObject("userList",users);
        return mv;
    }
    @RequestMapping("list")
    @ResponseBody
    public List<User> list(@RequestParam(value = "userName",required = false) String userName,
                           @RequestParam(value = "note",required = false) String note){
        List<User> users = userService.findUsers(userName,note);

        return users;
    }
    @RequestMapping("updateUserName")
    @ResponseBody
    public Map<String,Object> updateUserName(Long id, String userName){
        User user = userService.updateUser(id,userName);
        boolean flag = user !=null;
        String message = flag?"更新成功":"更新失败";
        return resultMap(flag,message);
    }
    @RequestMapping("/deleteUser")
    @ResponseBody
    public Map<String,Object> deleteUser(Long id){
        int result = userService.deleteUser(id);
        boolean flag = result ==1;
        String message = flag?"删除成功":"删除失败";
        return resultMap(flag,message);
    }
    private Map<String,Object> resultMap(boolean flag,String message){
        Map<String,Object> result = new HashMap<>();
        result.put("success",flag);
        result.put("message",message);
        return result;
    }


}
