/*package local.dw.resources;

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
import local.dw.dao.ProfilesDao;
import local.dw.service.ProfilesService;

@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("ADMIN")
public class ProfilesResource {

	private ProfilesDao profilesDao;
    private final AtomicLong counter;

	public ProfilesResource(ProfilesDao profilesDao) {
		this.profilesDao = profilesDao;
		this.counter = new AtomicLong();
	}

	
	@GET
	@Timed
	public Representation<List<Profile>> getProfiles() {
		List<Profile> profiles = profilesDao.getProfiles();
		return new Representation<List<Profile>>(HttpStatus.OK_200, profiles);
	}

	@GET
	@Timed
	@Path("{id}")
	public Representation<Profile> getProfile(@PathParam("id") final int id) {
		return new Representation<Profile>(HttpStatus.OK_200, profilesDao.getProfile(id));
	}

	@POST
	@Timed
	public Representation<Profile> createProfile(@NotNull @Valid final Profile profile) {
		profilesDao.createProfile(profile);
		return new Representation<Profile>(HttpStatus.OK_200, profilesDao.getProfile(profilesDao.lastInsertId()));
	}

	@PUT
	@Timed
	@Path("{id}")
	public Representation<Profile> editProfile(@NotNull @Valid final Profile profile, @PathParam("id") final int id) {
		profile.setId(id);
		profilesDao.editProfile(profile);
		return new Representation<Profile>(HttpStatus.OK_200, profile);
	}

	@DELETE
	@Timed
	@Path("{id}")
	public Representation<String> deleteProfile(@PathParam("id") final int id) {
		profilesDao.deleteProfile(id);
		return new Representation<String>(HttpStatus.OK_200, null);
	}

}
*/