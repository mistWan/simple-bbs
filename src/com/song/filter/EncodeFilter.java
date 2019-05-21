package com.song.filter;

import javax.servlet.*;
import java.io.IOException;

/*
* 字符编码过滤
* 处理中文数据乱码
* */
public class EncodeFilter implements Filter {
    private String encode;

    /*
    * 初始化过滤操作
    * */
    public void init(FilterConfig filterConfig) throws ServletException
    {
        /*
        * 获取 encode 设置
        * 文件在 WEB-INF/web.xml
        * */
        this.encode = filterConfig.getInitParameter("encode");
    }

    /*
    * 具体操作
    * */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
//        设置编码
        servletRequest.setCharacterEncoding(encode);
//        通知下一个过滤器进行操作
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
    }

}
