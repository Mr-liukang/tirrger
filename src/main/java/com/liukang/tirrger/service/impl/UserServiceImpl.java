package com.liukang.tirrger.service.impl;

import com.liukang.tirrger.dao.UserDao;
import com.liukang.tirrger.pojo.User;
import com.liukang.tirrger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1)
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1)
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public List<User> findUsers(String userName,String note) {
        return userDao.findUsers(userName,note);
    }
}
