package raf.rs.rafnews_webprogramiranje.resources;

import raf.rs.rafnews_webprogramiranje.entities.News;
import raf.rs.rafnews_webprogramiranje.entities.NewsTag;
import raf.rs.rafnews_webprogramiranje.services.NewsTagService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/newstags")

public class NewsTagResource {
    @Inject
    private NewsTagService newsTagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() throws SQLException {
        return Response.ok(this.newsTagService.allNewsTags()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public NewsTag create(@Valid NewsTag newsTag) throws SQLException {
        return this.newsTagService.addNewsTag(newsTag);
    }

    @GET
    @Path("/{tag_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> findNewsForTag(@PathParam("tag_id") Integer tag_id) throws SQLException {
        return this.newsTagService.findNewsForTag(tag_id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) throws SQLException {
        this.newsTagService.deleteNewsTag(id);
    }

}
