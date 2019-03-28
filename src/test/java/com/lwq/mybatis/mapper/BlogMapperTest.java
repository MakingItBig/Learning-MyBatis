package com.lwq.mybatis.mapper;

import com.lwq.mybatis.pojo.Blog;
import com.lwq.mybatis.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @ClassName BlogMapperTest
 * @Description 测试代码
 * @Author liu wq
 * @Date 2019/3/29 0:13
 * @Version 1.0
 */
public class BlogMapperTest {
    /**
     * 使用接口的测试方法
     */
    @Test
    public void testSelectBlog() {
        SqlSession session = MybatisUtil.openSqlSession();

        BlogMapper mapper = session.getMapper(BlogMapper.class);

        Blog blog = mapper.selectBlog(1);

        session.close();

        System.out.println(blog);
    }


    @Test
    public void testSelectBlog2() {
        SqlSession session = MybatisUtil.openSqlSession();

        BlogMapper mapper = session.getMapper(BlogMapper.class);

        Blog blog = mapper.selectBlog2(1);

        session.close();

        System.out.println(blog);
    }

    /**
     * 不使用接口的测试方法
     */
    @Test
    public void testSeclectBlogNoInterface() {
        SqlSession session = MybatisUtil.openSqlSession();
        try {
            Blog blog = (Blog) session.selectOne("com.lwq.mybatis.mapper.BlogMapper.selectBlog", 1);
            System.out.println(blog);
        } finally {
            session.close();
        }
    }
}
