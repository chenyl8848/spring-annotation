package com.spring.annotation.bean;

/**
 * @author cyl
 * @date 2022-08-21 21:58
 * @description 数据源
 */
public class DataSource {

    // 连接地址
    private String url;

    // 用户名
    private String userName;

    // 密码
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DataSource{" +
                "url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
