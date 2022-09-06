package com.spring.annotation.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author cyl
 * @date 2022-09-05 9:09
 * @description
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertUser() {
        String sql = "INSERT INTO `user`(username,password,email) VALUES (?, ?, ?);";
        String username = UUID.randomUUID().toString().substring(0, 5);
        String password = UUID.randomUUID().toString().substring(0, 5);
        String email = UUID.randomUUID().toString().substring(0, 5);
        jdbcTemplate.update(sql, username, password, email);
    }
}
