package local.dw.auth;

import java.security.Principal;
import java.util.Set;

public class User implements Principal {
	private int id;
	private String username;
	private Set<String> roles;

	public User(String username) {
		super();
		this.username = username;
		this.id = (int) (Math.random() * 100);
	}

	public User(String username, Set<String> roles) {
		this.username = username;
		this.roles = roles;
		this.id = (int) (Math.random() * 100);
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public String getName() {
		return username;
	}

	public Set<String> getRoles() {
		return roles;
	}

}
