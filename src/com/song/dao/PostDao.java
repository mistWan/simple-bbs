package com.song.dao;

import com.song.bean.Post;
import com.song.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PostDao
{
    /*
    * return 首页所有文章 List<Post>数据集
    * */
    public List<Post> getPosts()
    {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "SELECT id, title, content FROM post ORDER BY created_at DESC ";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Post> posts = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                posts.add(new Post(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content")));
            }
        } catch (Exception e) {
            System.out.println("获取文章失败");
            e.printStackTrace();
        } finally {
            ConnectionUtil.release(resultSet, preparedStatement, connection);
        }
        return posts;
    }

    /*
    * @params id 文章id
    * return 根据文章id返回该文章数据
    * */
    public Post getPost(int id)
    {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "SELECT post.id AS id, post.title AS title, post.content AS content, post.created_at AS created_at, user.name AS user_name FROM post LEFT JOIN user ON post.user_id = user.id WHERE post.id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Post post = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setTitle(resultSet.getString("title"));
                post.setContent(resultSet.getString("content"));
                post.setUserName(resultSet.getString("user_name"));
                post.setCreatedAt(resultSet.getTimestamp("created_at"));
            }
        } catch (SQLException e) {
            System.out.println("查询文章信息失败。");
            e.printStackTrace();
        } finally {
            ConnectionUtil.release(resultSet, preparedStatement, connection);
        }
        return post;
    }

    /*
    * @params post对象（包含前端的用户id、文章标题、文章内容、创建时间）
    * return 该文章是否创建成功
    * */
    public boolean store(Post post)
    {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO post(user_id, title, content, created_at) VALUES (?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, post.getId());
            preparedStatement.setString(2, post.getTitle());
            preparedStatement.setString(3, post.getContent());
            preparedStatement.setTimestamp(4, new Timestamp(post.getCreatedAt().getTime()));
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("文章创建失败！");
            e.printStackTrace();
            return false;
        } finally {
            ConnectionUtil.release(null, preparedStatement, connection);
        }
        return true;
    }

    /*
    * @params 用户id
    * return 根据用户id返回该用户的所有文章
    * */
    public List<Post> getUserPosts(int id)
    {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT id, title FROM post WHERE user_id = ? ORDER BY created_at DESC";
        ResultSet resultSet = null;
        List<Post> posts = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                posts.add(new Post(
                        resultSet.getInt("id"),
                        resultSet.getString("title")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.release(resultSet, preparedStatement, connection);
        }
        return posts;
    }

    /*
    * @params post对象（前端提交的文章标题、文章内容、文章id）
    * return 该文章是否更新成功
    * */
    public boolean update(Post post)
    {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE post SET title = ?, content = ? WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setInt(3, post.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("文章更新失败！");
            e.printStackTrace();
            return false;
        } finally {
            ConnectionUtil.release(null, preparedStatement, connection);
        }
        return true;
    }

    /*
    * @params 文章id
    * return 文章是否删除成功
    * */
    public boolean destroy(int id)
    {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM post WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("删除文章错误！");
            e.printStackTrace();
            return false;
        } finally {
            ConnectionUtil.release(null, preparedStatement, connection);
        }
        return true;
    }
}
