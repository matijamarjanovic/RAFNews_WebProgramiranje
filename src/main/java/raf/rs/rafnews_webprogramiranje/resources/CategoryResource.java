package raf.rs.rafnews_webprogramiranje.resources;

import raf.rs.rafnews_webprogramiranje.entities.Category;
import raf.rs.rafnews_webprogramiranje.entities.News;
import raf.rs.rafnews_webprogramiranje.services.CategoryService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/categories")
public class CategoryResource {

    @Inject
    private CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() throws SQLException {
        return Response.ok(this.categoryService.allCategories()).build();
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Category create(@Valid Category category) throws SQLException {
        return this.categoryService.addCategory(category);
    }
    @GET
    @Path("/single/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category findCategory(@PathParam("id") String id) throws SQLException { return this.categoryService.findCategory(id); }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> find(@PathParam("id") String id) throws SQLException { return this.categoryService.findNewsByCategory(id); }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) throws SQLException {
        this.categoryService.deleteCategory(id);
    }

    @POST
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Category updateCategory(@PathParam("id") String id, @Valid Category editedCategory){
        return this.categoryService.updateCategory(id, editedCategory); //TODO
    }
}
