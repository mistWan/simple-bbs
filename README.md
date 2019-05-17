# 简易论坛项目
Java Web项目

使用 `Jsp + Servlet + JDBC` 构建简易论坛项目



## 功能

用户注册、用户登录、用户退出

文章查看、文章发表、文章修改、文章删除

用户添加评论、评论查看、评论删除



## 技术点



### 页面

**Jsp + JSTl + JS + CSS**  
富文本编辑器采用 simditor



### 核心控制

**Filter + Servlet**



## 技术应用

- 三层设计 Servlet、Service、DAO (控制器、业务服务、持久化)

- Session 保存用户登录

- Filter 解决用户登录验证
- Filter 解决数据传输乱码



## 注意

JDK版本为1.8

mysql数据库默认账号为 root , 密码为空。请自行配置本机用户和密码

