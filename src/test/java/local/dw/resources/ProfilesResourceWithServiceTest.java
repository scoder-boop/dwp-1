package local.dw.resources;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import local.dw.service.ProfileSiteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import local.dw.api.Profile;
import local.dw.api.Representation;
import local.dw.service.ProfilesService;

@ExtendWith(DropwizardExtensionsSupport.class)
public class ProfilesResourceWithServiceTest {
//	 private static final Integer SUCCESS = "Success...";
	private static final Integer SUCCESS = 0;
	private static final String TEST_USER_NAME = "testUserName1";
	private static final String TEST_FIRST_NAME = "first";
	private static final String TEST_LAST_NAME = "last";
	private static final String EMAIL = "email";
	private static final String ADDRESS = "123 address";
	private static final int PHONE_NUMBER = 12345678;
	private static final boolean ACTIVE = true;
	private static final String PROFILES_ENDPOINT = "/profiles";

	private static final ProfilesService profilesService = mock(ProfilesService.class);
	private static final ProfileSiteService profileSiteService = mock(ProfileSiteService.class);
	public static final ResourceExtension RESOURCES = ResourceExtension.builder()
			.addResource(new ProfilesResourceWithService(profilesService, profileSiteService)).build();
	private ArgumentCaptor<Profile> profilesCaptor = ArgumentCaptor.forClass(Profile.class);
	private static Profile profile;

	@BeforeEach
	public void setup() {
		profile = new Profile(1, TEST_USER_NAME, TEST_FIRST_NAME, TEST_LAST_NAME, EMAIL, ADDRESS, PHONE_NUMBER, ACTIVE);

		when(profilesService.getProfile(eq(1))).thenReturn(profile);

		final List<Profile> profiles = Collections.singletonList(profile);
		when(profilesService.getProfiles()).thenReturn(profiles);
	}

	@AfterEach
	public void tearDown() {
		reset(profilesService);
	}

	@Test
	public void testGetProfile() {
		final TestProfileRepresentation representation = RESOURCES.target(PROFILES_ENDPOINT + "/1").request()
				.get(TestProfileRepresentation.class);
		final Profile profile = representation.getData();

		assertThat(representation.getCode()).isEqualTo(200);
		assertEquals(profile.getId(), profile.getId());
		assertEquals(profile.getUsername(), profile.getUsername());
		assertEquals(profile.getFirstName(), profile.getFirstName());
		assertEquals(profile.getLastName(), profile.getFirstName());
		assertEquals(profile.getEmail(), profile.getLastName());
		assertEquals(profile.getAddress(), profile.getAddress());
		assertEquals(profile.getPhoneNumber(), profile.getPhoneNumber());
		assertEquals(profile.isActive(), profile.isActive());
		verify(profilesService).getProfile(1);
	}

	@Test
	public void testGetProfiles() {

		TestProfilesRepresentation response = RESOURCES.target(PROFILES_ENDPOINT).request()
				.get(TestProfilesRepresentation.class);
		List<Profile> profiles = response.getData();

		assertThat(response.getCode()).isEqualTo(200);
		assertThat(profiles.size()).isEqualTo(1);
		assertEquals(profile.getId(), profile.getId());
		assertEquals(profile.getUsername(), profile.getUsername());
		assertEquals(profile.getFirstName(), profile.getFirstName());
		assertEquals(profile.getLastName(), profile.getFirstName());
		assertEquals(profile.getEmail(), profile.getLastName());
		assertEquals(profile.getAddress(), profile.getAddress());
		assertEquals(profile.getPhoneNumber(), profile.getPhoneNumber());
		assertEquals(profile.isActive(), profile.isActive());
		verify(profilesService).getProfiles();

	}

	@Test
	public void testCreateProfile() {
		when(profilesService.createProfile(any(Profile.class))).thenReturn(profile);
		final Response response = RESOURCES.target(PROFILES_ENDPOINT).request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(profile, MediaType.APPLICATION_JSON_TYPE));

		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
		verify(profilesService).createProfile(profilesCaptor.capture());
		assertEquals(profile.getId(), profile.getId());
		assertEquals(profile.getUsername(), profile.getUsername());
		assertEquals(profile.getFirstName(), profile.getFirstName());
		assertEquals(profile.getLastName(), profile.getFirstName());
		assertEquals(profile.getEmail(), profile.getLastName());
		assertEquals(profile.getAddress(), profile.getAddress());
		assertEquals(profile.getPhoneNumber(), profile.getPhoneNumber());
		assertEquals(profile.isActive(), profile.isActive());
	}

	@Test
	public void testEditProfile() {
		when(profilesService.editProfile(any(Profile.class))).thenReturn(profile);
		Profile editedProfile = RESOURCES.target(PROFILES_ENDPOINT + "/1").request(MediaType.APPLICATION_JSON_TYPE)
				.put(Entity.entity(profile, MediaType.APPLICATION_JSON_TYPE), TestProfileRepresentation.class)
				.getData();

		assertNotNull(editedProfile);
		assertEquals(profile.getId(), profile.getId());
		assertEquals(profile.getUsername(), profile.getUsername());
		assertEquals(profile.getFirstName(), profile.getFirstName());
		assertEquals(profile.getLastName(), profile.getFirstName());
		assertEquals(profile.getEmail(), profile.getLastName());
		assertEquals(profile.getAddress(), profile.getAddress());
		assertEquals(profile.getPhoneNumber(), profile.getPhoneNumber());
		assertEquals(profile.isActive(), profile.isActive());
		verify(profilesService).editProfile(any(Profile.class));
	}

	@Test
	public void testDeleteProfile() {
		assertThat(
				RESOURCES.target(PROFILES_ENDPOINT + "/1").request().delete(TestDeleteRepresentation.class).getCode())
						.isEqualTo(200);
		verify(profilesService).deleteProfile(1);
	}

	private static class TestProfileRepresentation extends Representation<Profile> {

	}

	public static class TestProfilesRepresentation extends Representation<List<Profile>> {

	}

	private static class TestDeleteRepresentation extends Representation<String> {

	}
}
