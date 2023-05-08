package kz.bitlab.techorda.db;

import java.sql.Timestamp;

public class News {
    private int id;
    private Timestamp post_Date;
    private NewsCategories categoryId;
    private String title;
    private String content;
    public News(){
    }

    public News(int id,
                Timestamp post_Date,
                NewsCategories categoryId,
                String title,
                String content) {
        this.id = id;
        this.post_Date = post_Date;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getPost_Date() {
        return post_Date;
    }

    public void setPost_Date(Timestamp post_Date) {
        this.post_Date = post_Date;
    }

    public NewsCategories getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(NewsCategories categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
