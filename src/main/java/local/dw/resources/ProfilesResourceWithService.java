package local.dw.resources;

import java.util.List;

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

import local.dw.api.ProfileSite;
import local.dw.service.ProfileSiteService;
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
	private ProfileSiteService profileSiteService;

	@Inject
	public ProfilesResourceWithService(ProfilesService profilesService, ProfileSiteService profileSiteService) {
		this.profilesService = profilesService;
		this.profileSiteService = profileSiteService;
	}

	
	@GET
	@Timed
	@Path("/active")
	public Representation<List<Profile>> getProfiles() {
		List<Profile> profiles = profilesService.getProfiles();
		return new Representation<List<Profile>>(HttpStatus.OK_200, profiles);
	}

	@GET
	@Timed
	@Path("/all")
	public Representation<List<Profile>> getAllProfiles() {
		List<Profile> profiles = profilesService.getAllProfiles();
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
		//TODO why?
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

	@GET
	@Timed
	@Path("{profileId}/{siteId}")
	public Representation<List<ProfileSite>> getProfileSite(@PathParam("profileId") final int profileId, @PathParam("siteId") final int siteId) {
		return new Representation<List<ProfileSite>>(HttpStatus.OK_200, profileSiteService.getProfileSite(profileId, siteId));
	}

	// PROFILE SITES
	@GET
	@Timed
	@Path("{profileId}/sites")
	public Representation<List<ProfileSite>> getProfileSites(@PathParam("profileId") final int id) {
		return new Representation<List<ProfileSite>>(HttpStatus.OK_200, profileSiteService.getProfileSites(id));
	}

	@POST
	@Timed
	@Path("{profileId}/site")
	public Representation<List<ProfileSite>> createProfileSite(@NotNull @Valid final ProfileSite profileSite, @PathParam("profileId") final int profileId) {
		return new Representation<List<ProfileSite>>(HttpStatus.OK_200, profileSiteService.createProfileSite(profileSite));
	}

	//TODO put List of sites

	@PUT
	@Timed
	@Path("{profileId}/site")
	public Representation<List<ProfileSite>> editProfileSite(@NotNull @Valid final ProfileSite profileSite, @PathParam("profileId") final int profileId) {
		//TODO Why?
		profileSite.setProfileId(profileId);
		return new Representation<List<ProfileSite>>(HttpStatus.OK_200, profileSiteService.editProfileSite(profileSite));
	}

}
