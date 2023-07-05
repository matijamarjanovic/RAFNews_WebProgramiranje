package raf.rs.rafnews_webprogramiranje.resources;

import raf.rs.rafnews_webprogramiranje.entities.Comment;
import raf.rs.rafnews_webprogramiranje.services.CommentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/comments")
public class CommentResource {

    @Inject
    private CommentService commentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() throws SQLException {
        return Response.ok(this.commentService.allComments()).build();
    }

    @GET
    @Path("/forNews/{news_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> allCommentsForNews(@PathParam("news_id") int newsId) throws SQLException { return this.commentService.allCommentsForNews(newsId); }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Comment create(@Valid Comment comment) throws SQLException {
        return this.commentService.addComment(comment);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment find(@PathParam("id") Integer id) throws SQLException {
        return this.commentService.findComment(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) throws SQLException {
        this.commentService.deleteComment(id);
    }
}
