package local.dw.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.dropwizard.auth.Auth;
import local.dw.auth.User;

@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    @GET
    @Path("/signin")
    public User signin(@Auth User user) {
        return user;
    }
}
