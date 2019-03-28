package com.lwq.mybatis.mapper;

import com.lwq.mybatis.pojo.Blog;

/**
 * @Interface BlogMapper
 * @Description 接口
 * @Author liu wq
 * @Date 2019/3/28 23:50
 * @Version 1.0
 */
public interface BlogMapper {

    /**
     * 查询接口
     * @param id
     * @return
     */
    Blog selectBlog(Integer id);

    Blog selectBlog2(Integer id);

}
