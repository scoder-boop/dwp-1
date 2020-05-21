package local.dw.api;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;

public class ProfileTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
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

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/profile.json"), Profile.class));

        assertThat(MAPPER.writeValueAsString(profile)).isEqualTo(expected);
    }
}
