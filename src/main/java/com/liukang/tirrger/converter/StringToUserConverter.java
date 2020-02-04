package com.liukang.tirrger.converter;

import com.liukang.tirrger.pojo.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToUserConverter implements Converter<String, User> {
    @Override
    public User convert(String userStr) {
        User user = new User();
        String[] split = userStr.split("-");
        Long id = Long.parseLong(split[0]);
        String userName = split[1];
        String note = split[2];
        user.setId(id);
        user.setUserName(userName);
        user.setNote(note);
        return user;
    }
}
