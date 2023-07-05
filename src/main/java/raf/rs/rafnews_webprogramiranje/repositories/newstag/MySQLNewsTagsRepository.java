package raf.rs.rafnews_webprogramiranje.repositories.newstag;

import raf.rs.rafnews_webprogramiranje.entities.News;
import raf.rs.rafnews_webprogramiranje.entities.NewsTag;
import raf.rs.rafnews_webprogramiranje.repositories.MySQLAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLNewsTagsRepository extends MySQLAbstractRepository implements NewsTagsRepository{
    @Override
    public NewsTag addNewsTag(NewsTag newstag) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        connection = this.newConnection();

        String[] generatedColumns = {"id"};

        statement = connection.prepareStatement("INSERT INTO newstags (newsId, tagId) VALUES (?, ?)", generatedColumns);
        statement.setInt(1, newstag.getNewsId());
        statement.setInt(2, newstag.getTagId());

        statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();

        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return newstag;
    }

    @Override
    public List<NewsTag> allNewsTags() throws SQLException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<NewsTag> allNewsTags = new ArrayList<>();

        connection = this.newConnection();
        statement = connection.createStatement();

        String query = "SELECT * from newstags";
        resultSet = statement.executeQuery(query);

        while(resultSet.next()){

            allNewsTags.add(new NewsTag(
                    resultSet.getInt("id"),
                    resultSet.getInt("tagId"),
                    resultSet.getInt("newsId")
            ));
        }

        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return allNewsTags;
    }

    @Override
    public List<News> findNewsForTag(int tagId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<NewsTag> allNewsTags = this.allNewsTags(); //prvo se dobiju sve postojece kombinacije news i tag
        //System.out.println("all news tags: "+ allNewsTags);
        ArrayList<News> allNewsByTag = new ArrayList<>(); //onda vracamo sve novosti za dati tag!
        ArrayList<Integer> newsIds = new ArrayList<>();

        for(NewsTag nt : allNewsTags){ //dodavanje svih id-eva 'news-a' koji idu uz dati tag
            if(nt.getTagId() == tagId)
                newsIds.add(nt.getNewsId());
            //System.out.println("are same?: " + nt.getId() +"=?"+ tagId + (nt.getId() == tagId));
        }

        connection = this.newConnection();
        statement = connection.createStatement();

        for(Integer i : newsIds){
            News news = null;
            String query = "SELECT * FROM news where id = " + i; //prolazi kroz sve date news_id-eve i fetchuje ih
            resultSet = statement.executeQuery(query);

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
                System.out.println(news);
                allNewsByTag.add(news); //i stavlja u ovaj niz
            }

        }
        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return allNewsByTag;
    }

    @Override
    public void deleteNewsTag(Integer id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        connection = this.newConnection();

        statement = connection.prepareStatement("DELETE FROM newstags where id = " + id);
        statement.executeUpdate();

        this.closeStatement(statement);
        this.closeConnection(connection);
    }
}
