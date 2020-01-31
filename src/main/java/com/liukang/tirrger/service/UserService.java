package com.liukang.tirrger.service;

import com.liukang.tirrger.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    User getUser(Long id);
    User insertUser(User user);
    User updateUser(Long id,String userName);
    List<User> findUsers(String userName,String note);
    int deleteUser(Long id);
}
