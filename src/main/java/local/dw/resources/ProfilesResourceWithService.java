package local.dw.resources;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.http.HttpStatus;
import com.codahale.metrics.annotation.Timed;

import local.dw.api.Profile;
import local.dw.api.Representation;
import local.dw.service.ProfilesService;

@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("ADMIN")
//@PermitAll
public class ProfilesResourceWithService {

	private ProfilesService profilesService;

	@Inject
	public ProfilesResourceWithService(ProfilesService profilesService) {
		this.profilesService = profilesService;
	}

	
	@GET
	@Timed
	public Representation<List<Profile>> getProfiles() {
		List<Profile> profiles = profilesService.getProfiles();
		return new Representation<List<Profile>>(HttpStatus.OK_200, profiles);
	}

	@GET
	@Timed
	@Path("/inactive")
	public Representation<List<Profile>> getInactiveProfiles() {
		List<Profile> profiles = profilesService.getInactiveProfiles();
		return new Representation<List<Profile>>(HttpStatus.OK_200, profiles);
	}

	@GET
	@Timed
	@Path("{id}")
	public Representation<Profile> getProfile(@PathParam("id") final int id) {
		return new Representation<Profile>(HttpStatus.OK_200, profilesService.getProfile(id));
	}

	@POST
	@Timed
	public Representation<Profile> createProfile(@NotNull @Valid final Profile profile) {
		return new Representation<Profile>(HttpStatus.OK_200, profilesService.createProfile(profile));
	}

	@PUT
	@Timed
	@Path("{id}")
	public Representation<Profile> editProfile(@NotNull @Valid final Profile profile, @PathParam("id") final int id) {
		profile.setId(id);
		return new Representation<Profile>(HttpStatus.OK_200, profilesService.editProfile(profile));
	}

	@DELETE
	@Timed
	@Path("{id}")
	public Representation<String> deleteProfile(@PathParam("id") final int id) {
		profilesService.deleteProfile(id);
		return new Representation<String>(HttpStatus.OK_200, null);
	}

}
