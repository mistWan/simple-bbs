package com.song.service;

import com.song.bean.User;
import com.song.dao.UserDao;

import java.util.Date;

public class UserService
{
    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    /*
     * 注册操作
     * @params User对象（包含前端提交的用户名、用户密码、创建时间）
     * return 该用户是否成功
     * */
    public boolean register(User user)
    {
        user.setCreatedAt(new Date());
        return userDao.register(user);
    }

    /*
     * 登录操作
     * @params1 用户名
     * @params2 用户密码
     * return User对象（用户id、用户名、用户密码、创建时间）
     * */
    public User login(String name, String password)
    {
        return userDao.login(name, password);
    }
}
