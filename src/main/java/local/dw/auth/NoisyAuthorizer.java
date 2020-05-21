package local.dw.auth;

import java.util.Objects;

import io.dropwizard.auth.Authorizer;
import local.dw.auth.User;

public class NoisyAuthorizer implements Authorizer<User> {
	@Override
	public boolean authorize(User principal, String role) {
		return principal.getRoles() != null && principal.getRoles().contains(role);
		// Allow any logged in user.
		/*
		 * if (Objects.nonNull(principal)) { return true; } return false;
		 */
	}

}
