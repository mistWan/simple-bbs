# [简易论坛项目](https://github.com/mistWan/simple-bbs)
Java Web项目

使用 `Jsp + Servlet + JDBC` 构建简易论坛项目

## 文件夹

### bean
存放MySQL数据库数据表字段  
普通的java对象（POJO）多数时候与DB表结构有对应关系，将DB中检索出数据，或者要往DB中反映的数据保存在实例中

### filter
过滤器文件夹，对用户请求进行预处理  
通过Filter技术，对web服务器管理的所有web资源：例如Jsp, Servlet, 静态图片文件或静态 html 文件等进行拦截，从而实现一些特殊的功能。例如实现URL级别的权限访问控制、过滤敏感词汇、压缩响应信息等一些高级功能。

### dao
Data Access Object数据访问接口，数据持久层，用来具体操作DB，完成增删改查

### service
业务逻辑层，用来调用不同的dao，完成特定的业务逻辑，并且DB的事务控制也在这层；

### servlet
控制层，接收客户端访问请求，并调用业务逻辑层完成相应处理，控制页面跳转；

## 功能

用户注册、用户登录、用户退出

文章查看、文章发表、文章修改、文章删除

用户添加评论、评论查看、评论删除



## 技术点



### 页面

**Jsp + JSTl + JS + CSS**  
富文本编辑器采用 [simditor](https://simditor.tower.im/)



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



