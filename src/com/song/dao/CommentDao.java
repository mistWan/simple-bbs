package com.song.dao;

import com.song.bean.Comment;
import com.song.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
* 评论操作
* */
public class CommentDao
{
    /*
    * @params id 文章id
    * return List<Comment> 根据文章id返回评论数据集
    * */
    public List<Comment> getComments(int id)
    {
//        数据驱动
        Connection connection = ConnectionUtil.getConnection();
//        查询语句
        String sql = "SELECT comment.content AS content, comment.created_at AS created_at,comment.post_id AS post_id, user.name AS user_name FROM comment LEFT JOIN  user ON comment.user_id = user.id WHERE comment.post_id = ? ORDER BY comment.created_at DESC";
//        初始化预编译得QL语句的对象，也就是说SQL语句被预编译并存储在PreparedStatement对象中，
//        然后可以使用此对象多次高效地执行改语句。
        PreparedStatement preparedStatement = null;
//        初始化查询数据库时
//        返回的是一个二维的结果集，我们需要用到ResultSet来遍历结果集，获取每一行的数据。
        ResultSet resultSet = null;
//        list集合对象
        List<Comment> comments = new ArrayList<>();
        try {
//         执行sql语句
            preparedStatement = connection.prepareStatement(sql);
//            有效的禁止sql语句的注入
            preparedStatement.setInt(1, id);
//            执行语句，返回结果
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                comments.add(new Comment(resultSet.getString("content"),
                        resultSet.getString("user_name"),
                        resultSet.getTimestamp("created_at")));
            }
        } catch (SQLException e) {
            System.out.println("获取评论失败");
            e.printStackTrace();
        } finally {
//            关闭数据库
            ConnectionUtil.release(resultSet, preparedStatement, connection);
        }
//        返回list对象
        return comments;
    }

    /*
    * @params comment对象（前端提交的评论内容、用户id、文章id、创建时间）
    * return bool 存储评论是否成功
    * */
    public boolean store(Comment comment)
    {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO comment(content, user_id, post_id, created_at) VALUES (?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, comment.getContent());
            preparedStatement.setInt(2, comment.getUserId());
            preparedStatement.setInt(3, comment.getPostId());
            preparedStatement.setTimestamp(4, new Timestamp(comment.getCreatedAt().getTime()));
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("存储评论失败！");
            e.printStackTrace();
            return false;
        } finally {
            ConnectionUtil.release(null, preparedStatement, connection);
        }
        return true;
    }

    /*
    * @params 用户id
    * return List<Comment> 根据用户id返回该用户的所有评论数据集
    * */
    public List<Comment> getUserComments(int id)
    {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT id, content, created_at FROM comment WHERE user_id = ? ORDER BY created_at DESC";
        ResultSet resultSet = null;
        List<Comment> comments = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                comments.add(new Comment(
                        resultSet.getInt("id"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("created_at")
                ));
            }
        } catch (SQLException e) {
            System.out.println("评论获取失败！");
            e.printStackTrace();
            return null;
        } finally {
            ConnectionUtil.release(resultSet, preparedStatement, connection);
        }
        return comments;
    }

    /*
    * params1 评论id
    * return 根据评论id删除该评论
    * */
    public boolean destroy(int id)
    {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM comment WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("评论删除错误！");
            e.printStackTrace();
            return false;
        } finally {
            ConnectionUtil.release(null, preparedStatement, connection);
        }
        return true;
    }
}
