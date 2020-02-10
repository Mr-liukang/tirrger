package com.liukang.tirrger.service.impl;

import com.liukang.tirrger.pojo.DataBaseRole;
import com.liukang.tirrger.pojo.User;
import com.liukang.tirrger.pojo.UserDetail;
import com.liukang.tirrger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(userName);
        System.out.println("=======");
        List<DataBaseRole> userROle = userService.findUserROle(userName);
        return changToUser(user,userROle);
    }
    private UserDetails changToUser(User user, List<DataBaseRole> roleList){
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for(DataBaseRole role:roleList){
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleId());
            authorityList.add(authority);
        }
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPwd(),authorityList);

        return userDetails;
    }
}
