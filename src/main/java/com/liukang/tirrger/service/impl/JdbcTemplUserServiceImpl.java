package com.liukang.tirrger.service.impl;

import com.liukang.tirrger.enumeration.SexEnum;
import com.liukang.tirrger.pojo.User;
import com.liukang.tirrger.service.JdbcTmplUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;
@Service
public class JdbcTemplUserServiceImpl implements JdbcTmplUserService {
    @Autowired
    private JdbcTemplate jdbcTemplate = null;

    private RowMapper<User> getUserRowMapper(){
    RowMapper<User> userRowMapper = (ResultSet rs ,int rownum)->{
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUserName(rs.getString("user_name"));
        user.setNote(rs.getString("note"));

        int sexId = rs.getInt("sex");
        SexEnum sex = SexEnum.getEnumById(sexId);
       // user.setSex(sex);
        return user;
    };
        return userRowMapper;
    }
    @Override
    public User getUser(Long id) {
        String sql = "select id,user_name,sex,note from t_user where id=?";
        Object[] params =  new Object[]{id};
        User user = jdbcTemplate.queryForObject(sql,params,getUserRowMapper());
        return user;
    }

    @Override
    public List<User> findUsers(String userName, String note) {

        return null;
    }

    @Override
    public int insertUser(User user) {
        return 0;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public int deleteUser(Long id) {
        return 0;
    }
}
