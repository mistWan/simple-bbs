package com.song.servlet;

import com.song.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// 评论删除
/*
* @params1 name servlet名字
* @params1 urlPatterns url路由
* */
@WebServlet(name = "CommentDeleteServlet", urlPatterns = {"/comment/delete/*"})
public class CommentDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
//        获取评论id
        int id = Integer.parseInt(request.getParameter("id"));
//        删除评论
        CommentService commentService = new CommentService();
        boolean destroy = commentService.destroy(id);
//        跳转到用户中心
        response.sendRedirect("/user");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
//        提交给doPost执行
        doPost(request, response);
    }
}
