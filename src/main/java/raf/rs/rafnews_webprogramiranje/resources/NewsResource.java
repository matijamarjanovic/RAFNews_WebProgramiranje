package raf.rs.rafnews_webprogramiranje.resources;

import raf.rs.rafnews_webprogramiranje.entities.News;
import raf.rs.rafnews_webprogramiranje.services.NewsService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Path("/news")
public class NewsResource {

    @Inject
    private NewsService newsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() throws SQLException {
        return Response.ok(this.newsService.allNews()).build();
    }
    @GET
    @Path("/mostViewed")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> mostReadNews() throws SQLException { return this.newsService.mostReadNews(); }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public News create(@Valid News news) throws SQLException {
        return this.newsService.addNews(news);
    }
    @POST
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public News updateNews(@PathParam("id") Integer id) throws SQLException {
        return this.newsService.updateNews(id);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public News find(@PathParam("id") Integer id) throws SQLException {
        return this.newsService.findNews(id);
    }
    @GET
    @Path("/forTag/{tag_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> find(@PathParam("tag_id") String tag_id) throws SQLException { return this.newsService.findNewsByTag(tag_id); }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) throws SQLException {
        this.newsService.deleteNews(id);
    }


}
