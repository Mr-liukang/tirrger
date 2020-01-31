package com.liukang.tirrger.service.impl;

import com.liukang.tirrger.dao.UserDao;
import com.liukang.tirrger.pojo.User;
import com.liukang.tirrger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    @Transactional
    @Cacheable(value = "redisCache",key = "'redis_user_'+#id")
    //@Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1)
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    //@Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1)
    @Transactional
    @CachePut(value = "redisCache",key = "'redis_user_'+#result.id")
    public User insertUser(User user) {
         userDao.insertUser(user);
        return user;
    }

    @Override
    @Transactional
    @CachePut(value = "redisCache",condition = "#result!='null'",key = "'redis_user_'+#id")
    public User updateUser(Long id,String userName) {
        User user  = this.getUser(id);
        if(user == null){
            return null;
        }
        user.setUserName(userName);
        userDao.updateUser(user);
        return user;
    }

    @Override
    @Transactional
    public List<User> findUsers(String userName,String note) {
        return userDao.findUsers(userName,note);
    }

    @Override
    @Transactional
    @CacheEvict(value = "redisCache",key = "'redis_user_'+#id",beforeInvocation = false)
    public int deleteUser(Long id) {
        return userDao.deleteUser(id);
    }
}
