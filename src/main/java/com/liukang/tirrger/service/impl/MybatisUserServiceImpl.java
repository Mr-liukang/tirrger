package com.liukang.tirrger.service.impl;

import com.liukang.tirrger.dao.MybatisUserDao;
import com.liukang.tirrger.pojo.User;
import com.liukang.tirrger.service.MybatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MybatisUserServiceImpl implements MybatisUserService {
    @Autowired
    MybatisUserDao mybatisUserDao;
    @Override
    public User getUser(Long id) {
        return mybatisUserDao.getUser(id);
    }
}
