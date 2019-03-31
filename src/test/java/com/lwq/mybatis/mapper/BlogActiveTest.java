package com.lwq.mybatis.mapper;

import com.lwq.mybatis.pojo.Blog;
import com.lwq.mybatis.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName BlogActiveTest
 * @Description 测试动态SQL if choose where
 * @Author liu wq
 * @Date 2019/3/29 10:04
 * @Version 1.0
 */
public class BlogActiveTest {

    @Test
    public void selectActiveBlogByTitleTest() {
        SqlSession session = MybatisUtil.openSqlSession();
        BlogActiveSql mapper = session.getMapper(BlogActiveSql.class);

        List<Blog> blog = mapper.selectActiveBlogByTitle("");

        session.close();
        System.out.println(blog);
    }

    @Test
    public void selectActiveBlogByTitleOrStyleTest() {
        SqlSession session = MybatisUtil.openSqlSession();
        BlogActiveSql mapper = session.getMapper(BlogActiveSql.class);

        Blog blog = new Blog();
        blog.setTitle("%o%");
        blog.setStyle("black");

        List<Blog> biogs = mapper.selectActiveBlogByTitleOrStyle(blog);

        session.close();
        System.out.println(biogs);
    }

    @Test
    public void selectActiveSqlByConditionTest() {
        SqlSession session = MybatisUtil.openSqlSession();
        BlogActiveSql mapper = session.getMapper(BlogActiveSql.class);

        Blog blog = new Blog();
        blog.setState("NOT ACTIVE");
        // blog.setTitle("%o%");

        List<Blog> biogs = mapper.selectActiveSqlByCondition(blog);

        session.close();
        System.out.println(biogs);
    }

    @Test
    public void updateBLogByCondition() {
        SqlSession session = MybatisUtil.openSqlSession();
        BlogActiveSql mapper = session.getMapper(BlogActiveSql.class);

        Blog blog = new Blog();
        blog.setId(1);
        blog.setTitle("水浒传");
        int count = mapper.updateBLogByCondition(blog);

        session.commit();
        session.close();

        System.out.println(count);
    }

    @Test
    public void updateBlogTest() {
        SqlSession session = MybatisUtil.openSqlSession();

        BlogActiveSql mapper = session.getMapper(BlogActiveSql.class);

        Blog blog = new Blog();
        blog.setId(5);
        blog.setStyle("blue");

        int count = mapper.updateBlog(blog);

        session.commit();
        session.close();

        System.out.println("更新了 " + count + "条数据！");
        System.out.println(blog);
    }

    @Test
    public void selectActiveSqlByConditionTrimTest() {
        SqlSession session = MybatisUtil.openSqlSession();
        BlogActiveSql mapper = session.getMapper(BlogActiveSql.class);

        Blog blog = new Blog();
        blog.setState("NOT ACTIVE");
        // blog.setTitle("%o%");

        List<Blog> biogs = mapper.selectActiveSqlByConditionTrim(blog);

        session.close();
        System.out.println(biogs);
    }

    @Test
    public void deleteActiveSqlByForeachTest() {
        SqlSession session = MybatisUtil.openSqlSession();
        BlogActiveSql mapper = session.getMapper(BlogActiveSql.class);

        List<Integer> ids = Arrays.asList(6, 7, 8);

        int count = mapper.deleteActiveSqlByForeach(ids);

        session.commit();
        session.close();
        System.out.println("删除了 " + count + " 条数据。");
    }
}
