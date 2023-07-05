package raf.rs.rafnews_webprogramiranje.services;

import raf.rs.rafnews_webprogramiranje.entities.Comment;
import raf.rs.rafnews_webprogramiranje.repositories.comment.CommentRepository;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

public class CommentService {
    @Inject
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment) throws SQLException { return this.commentRepository.addComment(comment); }

    public List<Comment> allCommentsForNews(Integer news_id) throws SQLException { return this.commentRepository.allCommentsForNews(news_id); }

    public List<Comment> allComments() throws SQLException {
        return this.commentRepository.allComments();
    }

    public Comment findComment(Integer id) throws SQLException {
        return this.commentRepository.findComment(id);
    }

    public void deleteComment(Integer id) throws SQLException {
        this.commentRepository.deleteComment(id);
    }
}
