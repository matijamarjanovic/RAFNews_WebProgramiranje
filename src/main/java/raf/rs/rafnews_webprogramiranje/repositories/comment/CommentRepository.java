package raf.rs.rafnews_webprogramiranje.repositories.comment;

import raf.rs.rafnews_webprogramiranje.entities.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentRepository {
    public Comment addComment(Comment comment) throws SQLException;
    public List<Comment> allCommentsForNews(Integer news_id) throws SQLException;
    public List<Comment> allComments() throws SQLException;
    public Comment findComment(Integer id) throws SQLException;
    public void deleteComment(Integer id) throws SQLException;
}
