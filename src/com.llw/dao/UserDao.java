package com.llw.dao;

import com.llw.pojo.User;

public interface UserDao {



    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 如果返回null，表示没有这个用户名存在，反之亦然
     */
    public User queryUserByUsername(String username);

    /**
     *  根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回null，表示没有这个用户存在，反之亦然
     */
    public User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 保存用户信息
     * @param user
     * @return 返回-1表示保存失败，返回其他表示成功
     */
    public int saveUser(User user);

}
