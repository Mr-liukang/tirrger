package com.liukang.tirrger.service;

import com.liukang.tirrger.pojo.User;

import java.util.List;

public interface UserService {
    User getUser(Long id);
    int insertUser(User user);
    List<User> findUsers(String userName,String note);
}
