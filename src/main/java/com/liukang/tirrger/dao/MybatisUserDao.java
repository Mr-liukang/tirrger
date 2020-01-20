package com.liukang.tirrger.dao;

import com.liukang.tirrger.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface MybatisUserDao {
    public User getUser(Long id);
}
