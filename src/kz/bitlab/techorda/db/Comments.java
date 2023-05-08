package kz.bitlab.techorda.db;

import java.sql.Timestamp;

public class Comments {
    private int id;
    private String comment;
    private Timestamp postTime;
    private Users user;
    private News news;
    public Comments(){
    }

    public Comments(int id,
                    String comment,
                    Timestamp postTime,
                    Users user,
                    News news) {
        this.id = id;
        this.comment = comment;
        this.postTime = postTime;
        this.user = user;
        this.news = news;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
