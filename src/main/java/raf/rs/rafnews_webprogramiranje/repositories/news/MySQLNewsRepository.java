package raf.rs.rafnews_webprogramiranje.repositories.news;

import raf.rs.rafnews_webprogramiranje.entities.News;
import raf.rs.rafnews_webprogramiranje.repositories.MySQLAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLNewsRepository extends MySQLAbstractRepository implements NewsRepository {
    @Override
    public News addNews(News news) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        connection = this.newConnection();

        String[] generatedColumns = {"id"};

        statement = connection.prepareStatement("INSERT INTO news (title, content, timeCreated, noVisits, authorId, categoryId ) VALUES (?, ?, ?, ?, ?, ?)", generatedColumns);
        statement.setString(1, news.getTitle());
        statement.setString(2, news.getText());
        statement.setDate(3, new java.sql.Date(news.getPublishDate().getTime()));
        statement.setInt(4, news.getVisits());
        statement.setInt(5, news.getAuthor());
        statement.setInt(6, news.getCategory());

        statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();

        if(resultSet.next()){
            news.setId(resultSet.getInt(1));
        }
        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return news;

    }

    @Override
    public News updateNews(Integer id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        News news = this.findNews(id);

        connection = this.newConnection();
        preparedStatement = connection.prepareStatement("UPDATE news SET title = " + '"'+ news.getTitle()  + '"' + " , content = " + '"' +  news.getText() + '"' + " WHERE id = " + id);

        int i1 = preparedStatement.executeUpdate();
        //System.out.println(i1 + "------------------------------------------------------");

        return news;
    }

    @Override
    public void incrementVisits(Integer id) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;


        connection = this.newConnection();
        statement = connection.createStatement();
        preparedStatement = statement.getConnection().prepareStatement("UPDATE news SET noVisits = noVisits + 1 WHERE id = " + id);
        preparedStatement.executeUpdate();

        this.closeStatement(statement);
        this.closeConnection(connection);
    }

    @Override
    public List<News> allNews() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<News> allNews = new ArrayList<>();

        connection = this.newConnection();
        statement = connection.createStatement();

        String query = "SELECT * from news ORDER BY timeCreated DESC";
        resultSet = statement.executeQuery(query);

        while(resultSet.next()){

            allNews.add(new News(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("content"),
                    resultSet.getDate("timeCreated"),
                    resultSet.getInt("categoryId"),
                    resultSet.getInt("authorId"),
                    resultSet.getInt("noVisits")
            ));
        }

        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return allNews;
    }

    @Override
    public List<News> mostReadNews() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<News> allNews = new ArrayList<>();

        connection = this.newConnection();
        statement = connection.createStatement();

        String query = "SELECT * FROM news ORDER BY noVisits DESC LIMIT 10";
        resultSet = statement.executeQuery(query);

        while(resultSet.next()){

            allNews.add(new News(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("content"),
                    resultSet.getDate("timeCreated"),
                    resultSet.getInt("categoryId"),
                    resultSet.getInt("authorId"),
                    resultSet.getInt("noVisits")
            ));
        }
        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return allNews;

    }

    @Override
    public News findNews(Integer id) throws SQLException {
        News news = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        connection = this.newConnection();
        statement = connection.prepareStatement("SELECT * FROM news where id = " + id);

        resultSet = statement.executeQuery();

        if(resultSet.next()){
            news = new News(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("content"),
                    resultSet.getDate("timeCreated"),
                    resultSet.getInt("categoryId"),
                    resultSet.getInt("authorId"),
                    resultSet.getInt("noVisits")
            );
            this.incrementVisits(id);

            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);

            return news;
        }

        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return null;
    }

    @Override
    public List<News> findNewsByTag(String tagName) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        ArrayList<News> allNewsByTag = new ArrayList<>();

        connection = this.newConnection();

        statement = connection.createStatement();
        String query = "SELECT * FROM news AS n JOIN newstags AS nt ON nt.newsId = n.id JOIN tags AS t ON nt.tagsId = t.name WHERE t.name like " +  '"' + tagName + '"';
        resultSet = statement.executeQuery(query);

        while(resultSet.next()){
            allNewsByTag.add(new News(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("content"),
                    resultSet.getDate("timeCreated"),
                    resultSet.getInt("categoryId"),
                    resultSet.getInt("authorId"),
                    resultSet.getInt("noVisits")
            ));
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);

            return allNewsByTag;
        }
        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return null;
    }

    @Override
    public void deleteNews(Integer id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        statement = connection.prepareStatement("DELETE FROM comments WHERE newsId = " + id);
        statement.executeUpdate();

        statement = connection.prepareStatement("DELETE FROM newstags WHERE newsId = " + id);
        statement.executeUpdate();

        statement = connection.prepareStatement("DELETE FROM news where id = " + id); //brisanje same novosti
        statement.executeUpdate();

        this.closeStatement(statement);
        this.closeConnection(connection);

    }
}
