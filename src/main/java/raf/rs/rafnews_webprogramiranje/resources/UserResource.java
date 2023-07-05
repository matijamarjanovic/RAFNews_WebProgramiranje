package raf.rs.rafnews_webprogramiranje.resources;

import raf.rs.rafnews_webprogramiranje.entities.User;
import raf.rs.rafnews_webprogramiranje.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/users")

public class UserResource {

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() throws SQLException { return Response.ok(this.userService.allUsers()).build(); }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User create(@Valid User user) throws SQLException { return this.userService.addUser(user); }
}
