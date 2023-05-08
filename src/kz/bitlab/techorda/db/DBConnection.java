package kz.bitlab.techorda.db;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    public static Connection connection;
    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/finaltask",
                    "root",
                    "root");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public  static  Users getUser(String email){
        Users user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users where email = ?"
            );
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                user = new Users();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("full_name"));
                user.setRoleId(resultSet.getInt("role_id"));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }


    public static void addUser(Users user){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO  users (email, password, full_name, role_id) " +
                            "VALUES (?, ?, ?, ?)"
            );
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setInt(4, user.getRoleId());
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  static  Users getUserId(int id){
        Users user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users where id = ?"
            );
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                user = new Users();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("full_name"));
                user.setRoleId(resultSet.getInt("role_id"));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static void updateUser(Users user){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users " +
                            "SET " +
                            "email = ?, " +
                            "password = ?, " +
                            "full_name = ?, " +
                            "role_id = ?   " +
                            "WHERE  id = ?"
            );

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setInt(4, user.getRoleId());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteUser(int id){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE  FROM  users WHERE id = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<NewsCategories> getNewsCategories(){
        ArrayList<NewsCategories> newsCategories = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM news_categories"
            );

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                NewsCategories category = new NewsCategories();
                category.setId(resultSet.getInt("id"));
                category.setNameCategory(resultSet.getString("category_name"));
                newsCategories.add(category);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return newsCategories;
    }

    public static void addNews(News news){
        try {
            PreparedStatement statement = connection.prepareStatement(
              "INSERT INTO news (post_date, category_id, title, content) " +
                      "VALUES (NOW(), ?, ?, ?)"
            );
            statement.setInt(1,news.getCategoryId().getId());
            statement.setString(2,news.getTitle());
            statement.setString(3,news.getContent());
            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public  static ArrayList<News> getNews(){
        ArrayList<News> news = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT n.id, n.post_date, n.category_id, n.title, n.content, nc.category_name  " +
                            "FROM news AS n " +
                            "INNER JOIN news_categories AS nc ON n.category_id = nc.id " +
                            "ORDER BY n.post_date DESC"
            );

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                News news1 = new News();
                news1.setId(resultSet.getInt("id"));
                news1.setPost_Date(resultSet.getTimestamp("post_date"));
                news1.setTitle(resultSet.getString("title"));
                news1.setContent(resultSet.getString("content"));

                NewsCategories category = new NewsCategories();
                category.setId(resultSet.getInt("category_id"));
                category.setNameCategory(resultSet.getString("category_name"));

                news1.setCategoryId(category);

                news.add(news1);
            }
            statement.close();

        }catch (Exception e){
         e.printStackTrace();
        }
        return news;
    }

    public  static News getNewsById(int id){
        News news = null;
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT n.id, n.post_date, n.category_id, n.title, n.content, nc.category_name  " +
                            "FROM news AS n " +
                            "INNER JOIN news_categories AS nc ON n.category_id = nc.id " +
                            "WHERE n.id =? "
            );
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                news = new News();
                news.setId(resultSet.getInt("id"));
                news.setPost_Date(resultSet.getTimestamp("post_date"));
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));

                NewsCategories category = new NewsCategories();
                category.setId(resultSet.getInt("category_id"));
                category.setNameCategory(resultSet.getString("category_name"));

                news.setCategoryId(category);

            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }

    public static void updateNews(News news){
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE news " +
                            "SET " +
                            "category_id = ?, " +
                            "title = ?, " +
                            "content = ? " +
                            "WHERE id = ? "
            );
            statement.setInt(1, news.getCategoryId().getId());
            statement.setString(2, news.getTitle());
            statement.setString(3, news.getContent());
            statement.setInt(4, news.getId());
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteNews(int id){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM news WHERE id = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addComment(Comments comment){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO comments (comment, post_time, user_id, news_id ) " +
                            "VALUES (?, NOW(), ?, ?)"
            );

            statement.setString(1, comment.getComment());
            statement.setInt(2, comment.getUser().getId());
            statement.setInt(3, comment.getNews().getId());
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Comments> getComments(int id){
        ArrayList<Comments> comments  = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT c.id, c.comment, c.post_time, c.user_id, c.news_id, u.full_name, u.role_id " +
                            "FROM comments AS c " +
                            "INNER JOIN users AS u ON c.user_id = u.id " +
                            "WHERE c.news_id = ? " +
                            "ORDER BY c.post_time DESC "
            );
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Comments comment  = new Comments();
                comment.setId(resultSet.getInt("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setPostTime(resultSet.getTimestamp("post_time"));

                Users user = new Users();
                user.setId(resultSet.getInt("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setRoleId(resultSet.getInt("role_id"));

                comment.setUser(user);
                comments.add(comment);
            }
            statement.close();

        }catch (Exception e){
         e.printStackTrace();
        }
        return comments;
    }

    public static Comments getCommentById(int id){
        Comments comment = null;
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT c.id, c.comment, c.post_time, c.user_id " +
                            "FROM comments AS c " +
                            "INNER JOIN users AS u ON c.user_id = u.id " +
                            "WHERE c.id = ? "
            );
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
           if (resultSet.next()){
               comment  = new Comments();
               comment.setId(resultSet.getInt("id"));
               comment.setComment(resultSet.getString("comment"));
               comment.setPostTime(resultSet.getTimestamp("post_time"));

                Users user = new Users();
                user.setId(resultSet.getInt("user_id"));

                comment.setUser(user);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return comment;
    }

    public static void deleteComment(int id){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM comments WHERE id = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  static ArrayList<News> getSportNews(){
        ArrayList<News> news = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT n.id, n.post_date, n.category_id, n.title, n.content, nc.category_name  " +
                            "FROM news AS n " +
                            "INNER JOIN news_categories AS nc ON n.category_id = nc.id " +
                            "WHERE n.category_id = 1 " +
                            "ORDER BY n.post_date DESC"
            );

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                News news1 = new News();
                news1.setId(resultSet.getInt("id"));
                news1.setPost_Date(resultSet.getTimestamp("post_date"));
                news1.setTitle(resultSet.getString("title"));
                news1.setContent(resultSet.getString("content"));

                NewsCategories category = new NewsCategories();
                category.setId(resultSet.getInt("category_id"));
                category.setNameCategory(resultSet.getString("category_name"));

                news1.setCategoryId(category);

                news.add(news1);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }

    public  static ArrayList<News> getPoliticalNews(){
        ArrayList<News> news = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT n.id, n.post_date, n.category_id, n.title, n.content, nc.category_name  " +
                            "FROM news AS n " +
                            "INNER JOIN news_categories AS nc ON n.category_id = nc.id " +
                            "WHERE n.category_id = 2 " +
                            "ORDER BY n.post_date DESC"
            );

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                News news1 = new News();
                news1.setId(resultSet.getInt("id"));
                news1.setPost_Date(resultSet.getTimestamp("post_date"));
                news1.setTitle(resultSet.getString("title"));
                news1.setContent(resultSet.getString("content"));

                NewsCategories category = new NewsCategories();
                category.setId(resultSet.getInt("category_id"));
                category.setNameCategory(resultSet.getString("category_name"));

                news1.setCategoryId(category);

                news.add(news1);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }

    public  static ArrayList<News> getBusinessNews(){
        ArrayList<News> news = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT n.id, n.post_date, n.category_id, n.title, n.content, nc.category_name  " +
                            "FROM news AS n " +
                            "INNER JOIN news_categories AS nc ON n.category_id = nc.id " +
                            "WHERE n.category_id = 3 " +
                            "ORDER BY n.post_date DESC"
            );

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                News news1 = new News();
                news1.setId(resultSet.getInt("id"));
                news1.setPost_Date(resultSet.getTimestamp("post_date"));
                news1.setTitle(resultSet.getString("title"));
                news1.setContent(resultSet.getString("content"));

                NewsCategories category = new NewsCategories();
                category.setId(resultSet.getInt("category_id"));
                category.setNameCategory(resultSet.getString("category_name"));

                news1.setCategoryId(category);

                news.add(news1);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }

    public  static ArrayList<News> getMediaNews(){
        ArrayList<News> news = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT n.id, n.post_date, n.category_id, n.title, n.content, nc.category_name  " +
                            "FROM news AS n " +
                            "INNER JOIN news_categories AS nc ON n.category_id = nc.id " +
                            "WHERE n.category_id = 4 " +
                            "ORDER BY n.post_date DESC"
            );

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                News news1 = new News();
                news1.setId(resultSet.getInt("id"));
                news1.setPost_Date(resultSet.getTimestamp("post_date"));
                news1.setTitle(resultSet.getString("title"));
                news1.setContent(resultSet.getString("content"));

                NewsCategories category = new NewsCategories();
                category.setId(resultSet.getInt("category_id"));
                category.setNameCategory(resultSet.getString("category_name"));

                news1.setCategoryId(category);

                news.add(news1);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }

    public  static ArrayList<News> getScienceNews(){
        ArrayList<News> news = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT n.id, n.post_date, n.category_id, n.title, n.content, nc.category_name  " +
                            "FROM news AS n " +
                            "INNER JOIN news_categories AS nc ON n.category_id = nc.id " +
                            "WHERE n.category_id = 5 " +
                            "ORDER BY n.post_date DESC"
            );

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                News news1 = new News();
                news1.setId(resultSet.getInt("id"));
                news1.setPost_Date(resultSet.getTimestamp("post_date"));
                news1.setTitle(resultSet.getString("title"));
                news1.setContent(resultSet.getString("content"));

                NewsCategories category = new NewsCategories();
                category.setId(resultSet.getInt("category_id"));
                category.setNameCategory(resultSet.getString("category_name"));

                news1.setCategoryId(category);

                news.add(news1);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }



    public  static ArrayList<News> getSearchNews(String key){
        ArrayList<News> news = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT n.id, n.post_date, n.category_id, n.title, n.content, nc.category_name  " +
                            "FROM news AS n " +
                            "INNER JOIN news_categories AS nc ON n.category_id = nc.id " +
                            "WHERE LOWER(n.title) = LIKE LOWER(?) " +
                            "ORDER BY n.post_date DESC "
            );
            statement.setString(1, key);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                News news1 = new News();
                news1.setId(resultSet.getInt("id"));
                news1.setPost_Date(resultSet.getTimestamp("post_date"));
                news1.setTitle(resultSet.getString("title"));
                news1.setContent(resultSet.getString("content"));

                NewsCategories category = new NewsCategories();
                category.setId(resultSet.getInt("category_id"));
                category.setNameCategory(resultSet.getString("category_name"));

                news1.setCategoryId(category);

                news.add(news1);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }


}
