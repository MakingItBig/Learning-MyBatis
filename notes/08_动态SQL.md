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

```

接口：

```java

```

测试：

```java

```