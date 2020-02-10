package com.liukang.tirrger.pojo;

import java.util.List;

public class UserDetail {
  private Long id;
  private String userName;
  private String note;
  private List<DataBaseRole> dataBaseRoleList;

    public UserDetail(Long id, String userName, String note, List<DataBaseRole> dataBaseRoleList) {
        this.id = id;
        this.userName = userName;
        this.note = note;
        this.dataBaseRoleList = dataBaseRoleList;
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

    public List<DataBaseRole> getDataBaseRoleList() {
        return dataBaseRoleList;
    }

    public void setDataBaseRoleList(List<DataBaseRole> dataBaseRoleList) {
        this.dataBaseRoleList = dataBaseRoleList;
    }
}
