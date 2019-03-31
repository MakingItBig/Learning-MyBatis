package com.lwq.mybatis.mapper;

import com.lwq.mybatis.pojo.Author;
import com.lwq.mybatis.pojo.AuthorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author liuwq
 */
public interface AuthorMapper {
    long countByExample(AuthorExample example);

    int deleteByExample(AuthorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Author record);

    int insertSelective(Author record);

    List<Author> selectByExample(AuthorExample example);

    Author selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Author record, @Param("example") AuthorExample example);

    int updateByExample(@Param("record") Author record, @Param("example") AuthorExample example);

    int updateByPrimaryKeySelective(Author record);

    int updateByPrimaryKey(Author record);
}