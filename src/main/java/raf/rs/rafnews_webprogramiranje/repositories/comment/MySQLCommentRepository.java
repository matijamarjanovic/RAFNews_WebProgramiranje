package raf.rs.rafnews_webprogramiranje.repositories.comment;

import raf.rs.rafnews_webprogramiranje.entities.Comment;
import raf.rs.rafnews_webprogramiranje.repositories.MySQLAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCommentRepository extends MySQLAbstractRepository implements CommentRepository {
    @Override
    public Comment addComment(Comment comment) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        connection = this.newConnection();

        String[] generatedColumns = {"id"};

        statement = connection.prepareStatement("INSERT INTO comments (author, contetn, dateCreated, newsId) VALUES (?, ?, ?, ?)", generatedColumns);
        statement.setString(1, comment.getCommenter());
        statement.setString(2, comment.getText());
        statement.setDate(3, new java.sql.Date(comment.getDateOfCreation().getTime()));
        statement.setInt(4, comment.getNewsId());

        statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();

        if(resultSet.next()){
            comment.setId(resultSet.getInt(1));
        }

        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return comment;
    }

    @Override
    public List<Comment> allCommentsForNews(Integer newsId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        ArrayList<Comment> comments = new ArrayList<>();

        connection = this.newConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from comments where newsId = " + newsId + " ORDER BY date desc");

        System.out.println(resultSet);

        while(resultSet.next()){ //int id, String author, String text, Date dateOfCreation
            comments.add(new Comment(
                    resultSet.getInt("id"),
                    resultSet.getString("author"),
                    resultSet.getInt("authorId"),
                    resultSet.getString("contetn"),
                    resultSet.getDate("dateCreated"),
                    resultSet.getInt("newsId")
            ));

        }
        System.out.println(comments.size());

        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return comments;
    }

    @Override
    public List<Comment> allComments() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        ArrayList<Comment> comments = new ArrayList<>();

        connection = this.newConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from comments");

        while(resultSet.next()){
            comments.add(new Comment(
                    resultSet.getInt("id"),
                    resultSet.getString("author"),
                    resultSet.getInt("authorId"),
                    resultSet.getString("contetn"),
                    resultSet.getDate("dateCreated"),
                    resultSet.getInt("newsId")
            ));

        }

        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);

        return comments;

    }

    @Override
    public Comment findComment(Integer id) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        Comment comment = null;

        connection = this.newConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from comments where id = " + id);

        if(resultSet.next()){
            comment = new Comment(
                    resultSet.getInt("id"),
                    resultSet.getString("author"),
                    resultSet.getInt("authorId"),
                    resultSet.getString("contetn"),
                    resultSet.getDate("dateCreated"),
                    resultSet.getInt("newsId")
            );
        }
        this.closeStatement(statement);
        this.closeResultSet(resultSet);
        this.closeConnection(connection);
        return comment;

    }

    @Override
    public void deleteComment(Integer id) throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;

        connection = this.newConnection();

        statement = connection.prepareStatement("DELETE FROM comments where id = " + id);
        statement.executeUpdate();

        this.closeStatement(statement);
        this.closeConnection(connection);
    }
}
