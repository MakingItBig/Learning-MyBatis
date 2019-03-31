package com.lwq.mybatis.pojo;

public class Blog {
    private Integer id;

    private String title;

    private Integer authorId;

    private String state;

    private Byte featured;

    private String style;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Byte getFeatured() {
        return featured;
    }

    public void setFeatured(Byte featured) {
        this.featured = featured;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }

    @Override
    public String toString() {
        return "\n" + "id=" + id +
                ", title='" + title + '\'' +
                ", authorId=" + authorId +
                ", state='" + state + '\'' +
                ", featured=" + featured +
                ", style='" + style + '\'';
    }
}