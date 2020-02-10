package com.liukang.tirrger.dao;

import com.liukang.tirrger.pojo.DataBaseRole;
import com.liukang.tirrger.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    User getUser(Long id);
    User getUserByUserName(@Param("userName") String userName);
    int insertUser(User user);
    int updateUser(User user);
    List<User> findUsers(@Param("userName") String userName, @Param("note") String note);
    int deleteUser(Long id);
    List<DataBaseRole> findUserROle(@Param("userName") String userName);
}
