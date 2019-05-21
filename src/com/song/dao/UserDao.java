package com.song.dao;

import com.song.bean.User;
import com.song.util.ConnectionUtil;

import java.sql.*;

public class UserDao
{
    /*
    * 注册操作
    * @params User对象（包含前端提交的用户名、用户密码、创建时间）
    * return 该用户是否成功
    * */
    public boolean register(User user)
    {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "INSERT INTO user(name, password, created_at) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setTimestamp(3, new Timestamp(user.getCreatedAt().getTime()));
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("创建用户失败。");
            e.printStackTrace();
            return false;
        } finally {
            ConnectionUtil.release(null, preparedStatement, connection);
        }
        return true;
    }

    /*
    * 登录操作
    * @params1 用户名
    * @params2 用户密码
    * return User对象（用户id、用户名、用户密码、创建时间）
    * */
    public User login(String name, String password)
    {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT id, name, created_at FROM user WHERE name = ? AND password = ?";
        ResultSet resultSet = null;
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("用户登录失败。");
            return null;
        } finally {
            ConnectionUtil.release(resultSet, preparedStatement, connection);
        }
        return user;
    }
}
