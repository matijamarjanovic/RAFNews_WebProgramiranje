package raf.rs.rafnews_webprogramiranje.repositories.tag;

import raf.rs.rafnews_webprogramiranje.entities.NewsTag;
import raf.rs.rafnews_webprogramiranje.entities.Tag;
import raf.rs.rafnews_webprogramiranje.repositories.MySQLAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLTagRepository extends MySQLAbstractRepository implements TagRepository {
    @Override
    public Tag addTag(Tag tag) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        String[] generatedColumns = {"id"};

        connection = this.newConnection();

        statement = connection.prepareStatement("INSERT INTO tags (name) VALUES (?)", generatedColumns); //might have to change into "VALUE" if needed
        statement.setString(1, tag.getName());
        resultSet = statement.getGeneratedKeys();

        if(resultSet.next()){
            tag.setId(resultSet.getInt(1));
        }

        statement.executeUpdate();

        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return tag;
    }

    @Override
    public List<Tag> allTags() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Tag> allTags = new ArrayList<>();

        connection = this.newConnection();
        statement = connection.createStatement();

        String query = "SELECT * from tags";
        resultSet = statement.executeQuery(query);

        while(resultSet.next()){

            allTags.add(new Tag(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            ));
        }

        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return allTags;
    }

    @Override
    public Tag findTag(String name) throws SQLException {
        Tag tag = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        connection = this.newConnection();
        statement = connection.prepareStatement("SELECT * FROM tags where name like " + '"' + name + '"');

        resultSet = statement.executeQuery();

        if(resultSet.next()){
            tag = new Tag(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            );

            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);

            return tag;
        }

        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return null;

    }

    @Override
    public List<Tag> allTagsForNews(Integer news_id) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Tag> tags = new ArrayList<>();
        ArrayList<NewsTag> allNewsTags = new ArrayList<>();
        ArrayList<Integer> tagIDs = new ArrayList<>();

        connection = this.newConnection();
        statement = connection.createStatement();

        resultSet = statement.executeQuery("SELECT * from newstags"); //vadi sve news_tag-ove i stavlja u niz "allNewsTags"
        while(resultSet.next()){
            allNewsTags.add(new NewsTag(
                    resultSet.getInt("id"),
                    resultSet.getInt("tagsId"),
                    resultSet.getInt("newsId")
            ));
        }

        for(NewsTag nwsTg: allNewsTags){ //ide kroz "allNewsTags" i izdvaja sve kljuceve tagova koji idu uz prosledjenu vest u "tagIDs"
            if(nwsTg.getNewsId() == news_id)
                tagIDs.add(nwsTg.getTagId());
        }

        for(Integer tagID: tagIDs){ //vadi sve tagove sa kljucevima u "tagIDs" iz baze i stavlja u "tags"
            resultSet = statement.executeQuery("SELECT * FROM tags WHERE id = " + '"' + tagID + '"');

            if(resultSet.next()) {
                tags.add(new Tag(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }
        }

        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return tags; //vraca "tags"

    }

    @Override
    public void deleteTag(String name) throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;

        connection = this.newConnection();

        statement = connection.prepareStatement("DELETE FROM tags where name like " + '"' + name + '"'); //brisanje bez provere!!
        statement.executeUpdate();

        this.closeStatement(statement);
        this.closeConnection(connection);

    }
}
