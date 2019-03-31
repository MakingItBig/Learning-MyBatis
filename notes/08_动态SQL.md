# 动态SQL

## if 条件语句

**需求：**

1. 查询已激活的blog，并且blog的名字是包含指定查询字符串的记录
2. 如果用户没有输入任何查询条件，就显示所有已激活的blog

mapper：

```xml
<select id="selectActiveBlogByTitle" parameterType="String" resultMap="blogResultMap">
    select * from blog where state = 'ACTIVE'
    <if test="value != null and value != ''">
        and title like '%${value}%'
    </if>
</select>
```

接口：

```java
/**
* 动态SQL - if
* @param title
* @return
*/
List<Blog> selectActiveBlogByTitle(String title);
```

测试：

```java
@Test
public void selectActiveBlogByTitleTest() {
    SqlSession session = MybatisUtil.openSqlSession();
    BlogActiveSql mapper = session.getMapper(BlogActiveSql.class);

    List<Blog> blogs = mapper.selectActiveBlogByTitle("");

    session.close();
    System.out.println(blogs);
}
```

## choose

1. 查询已激活的blog
2. 如果用户输入了标题的查询关键字，则根据关键字查询
3. 否则根据blog的风格样式查询
4. 如果什么都没有输入，则显示推荐的博客

mapper：

```xml
<!-- choose -->
<select id="selectActiveBlogByTitleOrStyle" parameterType="Blog" resultMap="blogResultMap">
    select * from blog where state = 'ACTIVE'
    <choose >
        <when test="title != null and title != ''">
            and lower (title) like lower (#{title})
        </when>
        <when test="style != null and style != ''">
            and style = #{style}
        </when>
        <otherwise>
            and featured = true;
        </otherwise>
    </choose>
</select>
```

接口：

```java
/**
* 动态SQL - choose
* @param blog 实体类
* @return
*/
List<Blog> selectActiveBlogByTitleOrStyle(Blog blog);
```

测试：

```java
@Test
public void selectActiveBlogByTitleOrStyleTest() {
    SqlSession session = MybatisUtil.openSqlSession();
    BlogActiveSql mapper = session.getMapper(BlogActiveSql.class);

    Blog blog = new Blog();
    blog.setTitle("%o%");
    blog.setStyle("black");

    List<Blog> blogs = mapper.selectActiveBlogByTitleOrStyle(blog);

    session.close();
    System.out.println(blogs);
}
```

## where

**需求：** 多条件查询，根据状态、标题、是否被推荐

mapper：

```xml
<!-- where -->
<select id="selectActiveSqlByCondition" parameterType="Blog" resultMap="blogResultMap" >
    select * from blog
    <where>
        <if test="state != null and state != ''" >
            state = #{state}
        </if>
        <if test="title != null and title != ''">
            and title like #{title}
        </if>
        <if test="featured != null">
            and featured = #{featured}
        </if>
    </where>
</select>
```

接口：

```java
/**
* 动态SQL - where
* @param blog 实体类
* @return
*/
List<Blog> selectActiveSqlByCondition(Blog blog);
```

测试：

```java
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
```

## set

**需求：** 修改指定列，未指定的不修改

mapper：

```xml
<!-- set -->
<update id="updateBLogByCondition" parameterType="Blog" >
    update blog
    <set>
        <if test="title != null">title = #{title},</if>
        <!--<if test="authorId != null">author_id = #{authorId},</if>-->
        <if test="state != null">state = #{state},</if>
        <if test="featured != null">featured = #{featured},</if>
        <if test="style != null">style = #{style}</if>
    </set>
    <where>
        id = #{id}
    </where>
</update>
```

接口：

```java
/**
* 动态SQL - set
* @param blog
* @return
*/
int updateBLogByCondition(Blog blog);
```

测试：

```java
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
```

## trim

mapper：

```xml
<!-- trim -->
<select id="selectActiveSqlByConditionTrim" parameterType="Blog" resultMap="blogResultMap">
    select * from blog
    <trim prefix="where" prefixOverrides="and | or">
        <if test="state != null and state != ''">
            state = #{state}
        </if>
        <if test="title != null and title != ''">
            and title like #{title}
        </if>
        <if test="featured != null">
            and featured = #{featured}
        </if>
    </trim>
</select>
```

接口：

```java
/**
* 动态SQL - trim
* @param blog
* @return
*/
List<Blog>  selectActiveSqlByConditionTrim(Blog blog);
```

测试：

```java
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
```

## foreach

**需求：** 实现批量删除

mapper：

```xml
<!-- foreach -->
<delete id="deleteActiveSqlByForeach" parameterType="list">
    delete from blog where id in 
    <foreach collection="list" item="item" open="(" separator="," close=")">
        #{item}
    </foreach>
</delete>
```

接口：

```java
/**
* 动态SQL - foreach
* @param ids id 集合
* @return
*/
int deleteActiveSqlByForeach(List<Integer> ids);
```

测试：

```java
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
```