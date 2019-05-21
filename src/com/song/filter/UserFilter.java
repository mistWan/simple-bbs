package com.song.filter;

import com.song.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*
* @params1 filterName 过滤器名字
* @params2 urlPatterns 过滤的路由有评论删除、评论创建、文章创建、文章删除、文章编辑、文章创建、文章更新、用户中心
*                      如果用户没有登录，将不能进行以上操作
* */
@WebFilter(filterName = "UserFilter", urlPatterns =
        {"/comment/delete/*", "/comment/store", "/create", "/user/delete/*", "/user/edit/*", "/store", "/update", "/user"})
public class UserFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {}

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException
    {
//        获取用户session
        User user = (User) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
//        如果用户为空，则返回登录页面；如果用户登录，继续下一步操作
        if (user == null) {
            servletRequest.getRequestDispatcher("/login").forward(servletRequest, servletResponse);
        } else {
            servletRequest.setAttribute("user", user);
//            filterChain.doFilter(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {}

}
