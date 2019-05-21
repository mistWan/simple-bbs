package com.song.service;

import com.song.bean.Comment;
import com.song.bean.User;
import com.song.dao.CommentDao;
//import com.song.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class CommentService
{
    /*
    * 实例化CommentDao
    * */
    private CommentDao commentDao;

    public CommentService() {
        commentDao = new CommentDao();
    }

    /*
     * @params id 文章id
     * return List<Comment> 根据文章id返回评论数据集
     * */
    public List<Comment> getComments(int id)
    {
        return commentDao.getComments(id);
    }

    /*
     * @params comment对象（前端提交的评论内容、用户id、文章id、创建时间）
     * return bool 存储评论是否成功
     * */
    public boolean store(Comment comment)
    {
        comment.setCreatedAt(new Date());
        return commentDao.store(comment);
    }

    /*
     * @params 用户id
     * return List<Comment> 根据用户id返回该用户的所有评论数据集
     * */
    public List<Comment> getUserComments(int id)
    {
        return commentDao.getUserComments(id);
    }

    /*
     * params1 评论id
     * return 根据评论id删除该评论
     * */
    public boolean destroy(int id)
    {
        return commentDao.destroy(id);
    }

/*    public boolean destroy(int id)
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
    }*/
}
