package com.llw.service.impl;

import com.llw.dao.UserDao;
import com.llw.dao.impl.UserDaoImpl;
import com.llw.pojo.User;
import com.llw.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());

    }

    @Override
    public boolean existsUsername(String username) {
        return userDao.queryUserByUsername(username) != null;
    }
}
