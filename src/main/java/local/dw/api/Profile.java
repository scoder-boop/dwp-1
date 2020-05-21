package local.dw.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.logback.shaded.checkerframework.checker.nullness.qual.NonNull;

public class Profile {
	@NonNull
	private int id;
	@NonNull
	private String username;
	@NonNull
	private String firstName;
	@NonNull
	private String lastName;
	private String email;
	private String address;
	private int phoneNumber;
	private boolean active;

	public Profile() {
		super();
	}

	
	public Profile(@NonNull int id, @NonNull String username, @NonNull String firstName, @NonNull String lastName,
			String email, String address, int phoneNumber, boolean active) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.active = active;
	}


	@JsonProperty
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonProperty
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@JsonProperty
	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonProperty
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


	@Override
	public String toString() {
		return "Profile [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", address=" + address + ", phoneNumber=" + phoneNumber + ", active=" + active
				+ "]";
	}

}
