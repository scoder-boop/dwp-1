package local.dw.resources;

import com.codahale.metrics.annotation.Timed;
import local.dw.api.ProfileInterest;
import local.dw.api.Representation;
import local.dw.service.ProfileInterestService;
import org.eclipse.jetty.http.HttpStatus;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/profile-interest")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("ADMIN")
//@PermitAll
public class ProfileInterestResource {

	private ProfileInterestService profileInterestService;

	@Inject
	public ProfileInterestResource(ProfileInterestService profileInterestService) {
		this.profileInterestService = profileInterestService;
	}

	
	@GET
	@Timed
	@Path("/{id}}")
	public Representation<List<ProfileInterest>> getAllInterestsForProfile(@PathParam("id") final int id) {
		List<ProfileInterest> profileInterests = profileInterestService.getAllInterestsForProfile(id);
		return new Representation<List<ProfileInterest>>(HttpStatus.OK_200, profileInterests);
	}

	@POST
	@Timed
	public Representation<List<ProfileInterest>> createProfile(@NotNull @Valid final ProfileInterest profileInterest) {
		return new Representation<List<ProfileInterest>>(HttpStatus.OK_200, profileInterestService.createProfileInterest(profileInterest));
	}

	@PUT
	@Timed
	@Path("{id}")
	public Representation<ProfileInterest> editProfile(@NotNull @Valid final ProfileInterest profileInterest, @PathParam("id") final int id) {
		//TODO why?
		profileInterest.setId(id);
		return new Representation<ProfileInterest>(HttpStatus.OK_200, profileInterestService.editProfileInterest(profileInterest));
	}

	@DELETE
	@Timed
	@Path("{id}")
	public Representation<String> deleteProfile(@PathParam("id") final int id) {
		profileInterestService.deleteProfileInterest(id);
		return new Representation<String>(HttpStatus.OK_200, null);
	}
}
