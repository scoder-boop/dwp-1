package local.dw.api;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;

public class RepresentationTest {
	  private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
	  private static final String PART_JSON = "fixtures/profile.json";
	  private static final String TEST_USER_NAME = "testUserName1";
	  private static final String TEST_FIRST_NAME = "first";
	  private static final String TEST_LAST_NAME = "last";
	  private static final String EMAIL = "email";
	  private static final String ADDRESS = "123 address";
	  private static final int PHONE_NUMBER = 12345678;
	  private static final boolean ACTIVE = true;

	  @Test
	  public void serializesToJSON() throws Exception {
	    final Profile profile = new Profile(1, TEST_USER_NAME, TEST_FIRST_NAME, TEST_LAST_NAME, EMAIL, ADDRESS, PHONE_NUMBER, ACTIVE);

	    final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture(PART_JSON), Profile.class));

	    assertEquals(MAPPER.writeValueAsString(profile), expected);
	  }

	  @Test
	  public void deserializesFromJSON() throws Exception {
	    final Profile profile = new Profile(1, TEST_USER_NAME, TEST_FIRST_NAME, TEST_LAST_NAME, EMAIL, ADDRESS, PHONE_NUMBER, ACTIVE);

	    assertEquals(MAPPER.readValue(fixture(PART_JSON), Profile.class).getId(), profile.getId());
	    assertEquals(MAPPER.readValue(fixture(PART_JSON), Profile.class).getUsername(), profile.getUsername());
	    assertEquals(MAPPER.readValue(fixture(PART_JSON), Profile.class).getFirstName(), profile.getFirstName());
	    assertEquals(MAPPER.readValue(fixture(PART_JSON), Profile.class).getLastName(), profile.getFirstName());
	    assertEquals(MAPPER.readValue(fixture(PART_JSON), Profile.class).getEmail(), profile.getLastName());
	    assertEquals(MAPPER.readValue(fixture(PART_JSON), Profile.class).getAddress(), profile.getAddress());
	    assertEquals(MAPPER.readValue(fixture(PART_JSON), Profile.class).getPhoneNumber(), profile.getPhoneNumber());
	    assertEquals(MAPPER.readValue(fixture(PART_JSON), Profile.class).isActive(), profile.isActive());
	  }
	
}
