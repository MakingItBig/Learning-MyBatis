package com.lwq.mybatis.mapper;

import com.lwq.mybatis.pojo.Blog;

import java.util.List;

/**
 * @Interface BlogActive
 * @Description TODO
 * @Author liu wq
 * @Date 2019/3/29 10:03
 * @Version 1.0
 */
public interface BlogActiveSql {

    /**
     * 动态SQL - if
     * @param title
     * @return
     */
    List<Blog> selectActiveBlogByTitle(String title);

    /**
     * 动态SQL - choose
     * @param blog 实体类
     * @return
     */
    List<Blog> selectActiveBlogByTitleOrStyle(Blog blog);

    /**
     * 动态SQL - where
     * @param blog 实体类
     * @return
     */
    List<Blog> selectActiveSqlByCondition(Blog blog);

    /**
     * 动态SQL - set
     * @param blog
     * @return
     */
    int updateBLogByCondition(Blog blog);

    /**
     * 更新数据
     * @param blog Blogz类
     * @return
     */
    int updateBlog(Blog blog);

    /**
     * 动态SQL - trim
     * @param blog
     * @return
     */
    List<Blog>  selectActiveSqlByConditionTrim(Blog blog);

    /**
     * 动态SQL - foreach
     * @param ids id 集合
     * @return
     */
    int deleteActiveSqlByForeach(List<Integer> ids);
}
