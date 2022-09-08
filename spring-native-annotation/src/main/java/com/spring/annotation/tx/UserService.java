package com.spring.annotation.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cyl
 * @date 2022-09-05 9:08
 * @description
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    // 开启事务
    @Transactional(rollbackFor = Exception.class)
    public void insertUser() {
        System.out.println("新增用户信息....");
        userDao.insertUser();
        int i = 10 / 0;
    }
}
