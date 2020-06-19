package local.dw.dao;

import local.dw.api.Profile;
import local.dw.api.ProfileHistory;
import local.dw.mapper.ProfileMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;

@RegisterRowMapper(ProfileMapper.class)
public interface ProfilesDao {

	@SqlUpdate("insert into profile_history (id, profile_id, event_time, event_time_epoch, event) "
			+ "values(:id, :profileId, :eventTime, :eventTimeEpoch, :event);")
	public boolean addProfileHistory(@BindBean final ProfileHistory profileHistory);


	@SqlQuery("select id, username, first_name, last_name, email, address, phone_number, active from profile;")
	public List<Profile> getAllProfiles();

	@SqlQuery("select id, username, first_name, last_name, email, address, phone_number, active from profile where active = true;")
	  public List<Profile> getProfiles();
//	  public ResultIterable<Profile> getProfilesAsIterable();

	@SqlQuery("select id, username, first_name, last_name, email, address, phone_number, active from profile where active = false;")
	public List<Profile> getInactiveProfiles();

	@SqlQuery("select id, username, first_name, last_name, email, address, phone_number, active from profile where id = :id")
	public Profile getProfile(@Bind("id") final int id);

	@SqlUpdate("insert into profile(username, first_name, last_name, email, address, phone_number, active) "
			    + "values(:username, :firstName, :lastName, :email, :address, :phoneNumber, :active);")
	public boolean addProfile(@BindBean final Profile profile);

	@Transaction
	default Profile addProfileWithHistory (final Profile profile) {
		profile.setActive(true);
		addProfile(profile);
		int id = lastInsertId();
		profile.setId(id);
		ProfileHistory profileHistory = new ProfileHistory(id, "Profile Created: " + profile.toString());
		addProfileHistory(profileHistory);
		return getProfile(id);
	}

	@SqlUpdate("update profile "
			+ "set username = coalesce(:username, username)"
			+ ", first_name = coalesce(:firstName, first_name) "
			+ ", last_name = coalesce(:lastName, last_name) "
			+ ", email = coalesce(:email, email) "
			+ ", address = coalesce(:address, address) "
			+ ", phone_number = coalesce(:phoneNumber, phone_number) "
			+ ", active = coalesce(:active, active) "
			+ "where id = :id;")
	public boolean editProfile(@BindBean final Profile profile);

	@Transaction
	default boolean editProfileWithHistory(Profile profile) {
		boolean result = editProfile(profile);
		ProfileHistory profileHistory = new ProfileHistory(profile.getId(), "Profile Amended to: " + profile.toString());
		addProfileHistory(profileHistory);
		return result;
	}

	@SqlUpdate("delete from profile where id = :id;")
	public boolean deleteProfile(@Bind("id") final int id);

	@Transaction
	default boolean deleteProfileWithHistory(int id) {
		boolean result = deleteProfile(id);
		ProfileHistory profileHistory = new ProfileHistory(id, "Profile Deleted: " + id);
		addProfileHistory(profileHistory);
		return result;
	}

	// thread safe - is per connection
	@SqlQuery("select last_insert_id();")
	public int lastInsertId();
}
