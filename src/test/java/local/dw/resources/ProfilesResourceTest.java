/*package local.dw.resources;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
import local.dw.dao.ProfilesDao;

@ExtendWith(DropwizardExtensionsSupport.class)
public class ProfilesResourceTest {
private static final String TEST_USER_NAME = "testUserName1";
	private static final String TEST_FIRST_NAME = "first";
	private static final String TEST_LAST_NAME = "last";
	private static final String EMAIL = "email";
	private static final String ADDRESS = "123 address";
	private static final int PHONE_NUMBER = 12345678;
	private static final boolean ACTIVE = true;
	private static final String PROFILES_ENDPOINT = "/profiles";

	private static final ProfilesDao profilesDao = mock(ProfilesDao.class);
	public static final ResourceExtension RESOURCES = ResourceExtension.builder()
			.addResource(new ProfilesResource(profilesDao)).build();
	private ArgumentCaptor<Profile> profilesCaptor = ArgumentCaptor.forClass(Profile.class);
	private static Profile profile;

	@BeforeEach
	public void setup() {
		profile = new Profile(1, TEST_USER_NAME, TEST_FIRST_NAME, TEST_LAST_NAME, EMAIL, ADDRESS, PHONE_NUMBER, ACTIVE);

		when(profilesDao.getProfile(eq(1))).thenReturn(profile);

		final List<Profile> profiles = Collections.singletonList(profile);
		when(profilesDao.getProfiles()).thenReturn(profiles);
	}

	@AfterEach
	public void tearDown() {
		reset(profilesDao);
	}

	@Test
	public void testGetProfile() {
		final TestProfileRepresentation representation = RESOURCES.target(PROFILES_ENDPOINT + "/1").request()
				.get(TestProfileRepresentation.class);
		final Profile profile = representation.getData();

		assertThat(representation.getCode()).isEqualTo(200);
		assertEquals(profile.getId(), profile.getId());
		assertEquals(profile.getUserName(), profile.getUserName());
		assertEquals(profile.getFirstName(), profile.getFirstName());
		assertEquals(profile.getLastName(), profile.getFirstName());
		assertEquals(profile.getEmail(), profile.getLastName());
		assertEquals(profile.getAddress(), profile.getAddress());
		assertEquals(profile.getPhoneNumber(), profile.getPhoneNumber());
		assertEquals(profile.isActive(), profile.isActive());
		verify(profilesDao).getProfile(1);
	}

	@Test
	public void testGetProfiles() {

		TestProfilesRepresentation response = RESOURCES.target(PROFILES_ENDPOINT).request()
				.get(TestProfilesRepresentation.class);
		List<Profile> profiles = response.getData();

		assertThat(response.getCode()).isEqualTo(200);
		assertThat(profiles.size()).isEqualTo(1);
		assertEquals(profile.getId(), profile.getId());
		assertEquals(profile.getUserName(), profile.getUserName());
		assertEquals(profile.getFirstName(), profile.getFirstName());
		assertEquals(profile.getLastName(), profile.getFirstName());
		assertEquals(profile.getEmail(), profile.getLastName());
		assertEquals(profile.getAddress(), profile.getAddress());
		assertEquals(profile.getPhoneNumber(), profile.getPhoneNumber());
		assertEquals(profile.isActive(), profile.isActive());
		verify(profilesDao).getProfiles();

	}

	@Test
	public void testCreateProfile() {
		when(profilesDao.createProfile(any(Profile.class))).thenReturn(true);
		final Response response = RESOURCES.target(PROFILES_ENDPOINT).request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(profile, MediaType.APPLICATION_JSON_TYPE));

		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
		verify(profilesDao).createProfile(profilesCaptor.capture());
		assertEquals(profile.getId(), profile.getId());
		assertEquals(profile.getUserName(), profile.getUserName());
		assertEquals(profile.getFirstName(), profile.getFirstName());
		assertEquals(profile.getLastName(), profile.getFirstName());
		assertEquals(profile.getEmail(), profile.getLastName());
		assertEquals(profile.getAddress(), profile.getAddress());
		assertEquals(profile.getPhoneNumber(), profile.getPhoneNumber());
		assertEquals(profile.isActive(), profile.isActive());
	}

	@Test
	public void testEditProfile() {

		Profile editedProfile = RESOURCES.target(PROFILES_ENDPOINT + "/1").request()
				.put(Entity.entity(profile, MediaType.APPLICATION_JSON_TYPE), TestProfileRepresentation.class)
				.getData();

		assertNotNull(editedProfile);
		assertEquals(profile.getId(), profile.getId());
		assertEquals(profile.getUserName(), profile.getUserName());
		assertEquals(profile.getFirstName(), profile.getFirstName());
		assertEquals(profile.getLastName(), profile.getFirstName());
		assertEquals(profile.getEmail(), profile.getLastName());
		assertEquals(profile.getAddress(), profile.getAddress());
		assertEquals(profile.getPhoneNumber(), profile.getPhoneNumber());
		assertEquals(profile.isActive(), profile.isActive());
		verify(profilesDao).editProfile(any(Profile.class));
	}

	@Test
	public void testDeleteProfile() {
		assertThat(
				RESOURCES.target(PROFILES_ENDPOINT + "/1").request().delete(TestDeleteRepresentation.class).getCode())
						.isEqualTo(200);
		verify(profilesDao).deleteProfile(1);
	}

	private static class TestProfileRepresentation extends Representation<Profile> {

	}

	public static class TestProfilesRepresentation extends Representation<List<Profile>> {

	}

	private static class TestDeleteRepresentation extends Representation<String> {

	}
}
*/