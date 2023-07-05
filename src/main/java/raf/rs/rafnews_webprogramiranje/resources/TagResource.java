package raf.rs.rafnews_webprogramiranje.resources;

import raf.rs.rafnews_webprogramiranje.entities.Tag;
import raf.rs.rafnews_webprogramiranje.services.TagService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/tags")
public class TagResource {

    @Inject
    private TagService tagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() throws SQLException {
        return Response.ok(this.tagService.allTags()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Tag create(@Valid Tag tag) throws SQLException {
        return this.tagService.addTag(tag);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tag find(@PathParam("id") String name) throws SQLException { return this.tagService.findTag(name); }

    @GET
    @Path("/forNews/{news_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tag> allTagsForNews(@PathParam("news_id") int newsId) throws SQLException { return this.tagService.allTagsForNews(newsId); }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) throws SQLException {
        this.tagService.deleteTag(id);
    }
}
