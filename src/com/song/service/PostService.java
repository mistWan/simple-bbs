package com.song.service;

import com.song.bean.Post;
import com.song.dao.PostDao;
import com.song.util.HtmlToText;

import java.util.Date;
import java.util.List;

/*
* 业务逻辑。
* 在这个层里面对数据进行逻辑处理 。
* 例如:我要对前台拿到的数据进行数据库插入 。
* 那么我要对数据进行一定的组装 ，判断，设置等等之后再插入数据库中去。
* service就是做上面的事情。这只是个例子 具体还是要看你所需的业务逻辑
* */
public class PostService
{
    private PostDao postDao;

    public PostService() {
        postDao = new PostDao();
    }

    /*
     * return 首页所有文章 List<Post>数据集
     * */
    public List<Post> getPosts()
    {
        List<Post> posts =  postDao.getPosts();
        HtmlToText htmlToText = new HtmlToText();
        for (Post post : posts) {
            post.setContent(htmlToText.getText(post.getContent()));
        }
        return posts;
    }

    /*
     * @params id 文章id
     * return 根据文章id返回该文章数据
     * */
    public Post getPost(int id)
    {
        return postDao.getPost(id);
    }

    /*
     * @params post对象（包含前端的用户id、文章标题、文章内容、创建时间）
     * return 该文章是否创建成功
     * */
    public boolean store(Post post)
    {
        post.setCreatedAt(new Date());
        return postDao.store(post);
    }

    /*
     * @params 用户id
     * return 根据用户id返回该用户的所有文章
     * */
    public List<Post> getUserPosts(int id)
    {
        return postDao.getUserPosts(id);
    }

    /*
     * @params post对象（前端提交的文章标题、文章内容、文章id）
     * return 该文章是否更新成功
     * */
    public boolean update(Post post)
    {
        return postDao.update(post);
    }

    /*
     * @params 文章id
     * return 文章是否删除成功
     * */
    public boolean destroy(int id)
    {
        return postDao.destroy(id);
    }
}
