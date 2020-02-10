package com.liukang.tirrger.service;

import com.liukang.tirrger.pojo.DataBaseRole;
import com.liukang.tirrger.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    User getUser(Long id);
    User getUserByUserName(String userName);
    User insertUser(User user);
    User updateUser(Long id,String userName);
    List<User> findUsers(String userName,String note);
    int deleteUser(Long id);
    List<DataBaseRole> findUserROle(String userName);
}
