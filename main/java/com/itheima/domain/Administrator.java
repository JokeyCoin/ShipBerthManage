package com.itheima.domain;
import java.io.Serializable;
public class Administrator implements Serializable {
    private Integer id;       //用户id
    private String username;  //用户名
    private String password;  //密码
    private String name;      //用户昵称
    private String role;      //用户角色
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

}
