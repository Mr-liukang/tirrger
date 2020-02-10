package com.liukang.tirrger.pojo;

import org.apache.ibatis.type.Alias;

@Alias("dataBaseRole")
public class DataBaseRole {
    private  Long id ;
    private  String userId;
    private  String roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
