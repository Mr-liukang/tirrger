package com.liukang.tirrger.pojo;

import com.liukang.tirrger.enumeration.SexEnum;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Alias("user")
public class User implements Serializable {
    private static  final long serialVersionUID = -8635252367552884309L;

    @Value("1")
    private Long id ;
    @Value("userName")
    private String userName;
    private SexEnum sex;
    @Value("note")
    private String note ;
    private String pwd;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }
}
