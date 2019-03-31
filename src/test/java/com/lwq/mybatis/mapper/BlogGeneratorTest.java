package com.lwq.mybatis.mapper;

import com.lwq.mybatis.pojo.Blog;
import com.lwq.mybatis.pojo.BlogExample;
import com.lwq.mybatis.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName BlogGeneratorTest
 * @Description TODO
 * @Author liu wq
 * @Date 2019/3/30 15:19
 * @Version 1.0
 */
public class BlogGeneratorTest {

    @Test
    public void testSelect() {
        SqlSession session = MybatisUtil.openSqlSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);

        Blog blog = mapper.selectByPrimaryKey(1);

        session.close();

        System.out.println("查询结果：" + blog);
    }

    @Test
    public void testSelectByExample() {
        SqlSession session = MybatisUtil.openSqlSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);

        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andStateLike("%ACTIVE%");
        List<Blog> blogs = mapper.selectByExample(example);

        session.close();

        System.out.println("查询结果 ：\n" + blogs);
    }
}
