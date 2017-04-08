package com.doublefly.wfs.mvp_retrofit_rxjava.annotation;

/**
 * Created by WFS on 2017/4/8.
 */
@Table("user")
public class UserBean {
    @Columns("username")
    private String username;

    public String getUsername() {
        return username;
    }

    @Columns("password")
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
