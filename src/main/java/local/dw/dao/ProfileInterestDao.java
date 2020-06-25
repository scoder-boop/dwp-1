package local.dw.dao;

import local.dw.api.ProfileHistory;
import local.dw.api.ProfileInterest;
import local.dw.mapper.ProfileInterestMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;

@RegisterRowMapper(ProfileInterestMapper.class)
public interface ProfileInterestDao {

	@SqlUpdate("insert into profile_history (id, profile_id, event_time, event_time_epoch, event) "
			+ "values(:id, :profileId, :eventTime, :eventTimeEpoch, :event);")
	public boolean addProfileHistory(@BindBean final ProfileHistory profileHistory);

	@SqlQuery("select id, profile_id, topic, active from profile_interest  where id = :id")
	public List<ProfileInterest> getProfileInterest(@Bind("id") final int id);

	@SqlQuery("select id, profile_id, topic, active from profile_interest  where profile_id = :profileId")
	public List<ProfileInterest> getAllInterestsForProfile(@Bind("profileId") final int profileId);

	@SqlUpdate("insert into profile_interest(profile_id, topic, active) "
			    + "values(:profile_id, :topic, :active);")
	public boolean addProfileInterest(@BindBean final ProfileInterest profileInterest);

	@Transaction
	default List<ProfileInterest> addProfileInterestWithHistory (final ProfileInterest profileInterest) {
		profileInterest.setActive(true);
		addProfileInterest(profileInterest);
		int id = lastInsertId();
		profileInterest.setId(id);
		ProfileHistory profileHistory = new ProfileHistory(id, "Profile Interest Created: " + profileInterest.toString());
		addProfileHistory(profileHistory);
		return getAllInterestsForProfile(id);
	}

	@SqlUpdate("update profile_interest "
			+ "set topic = coalesce(:topic, topic)"
			+ ", active = coalesce(:active, active) "
			+ "where id = :id;")
	public boolean editProfileInterest(@BindBean final ProfileInterest profileInterest);

	@Transaction
	default boolean editProfileInterestWithHistory(ProfileInterest profileInterest) {
		boolean result = editProfileInterest(profileInterest);
		ProfileHistory profileHistory = new ProfileHistory(profileInterest.getId(), "Profile Interest Amended to: " + profileInterest.toString());
		addProfileHistory(profileHistory);
		return result;
	}

	@SqlUpdate("delete from profile_interest where id = :id;")
	public boolean deleteProfileInterest(@Bind("id") final int id);

	@Transaction
	default boolean deleteProfileInterestWithHistory(int id) {
		boolean result = deleteProfileInterest(id);
		ProfileHistory profileHistory = new ProfileHistory(id, "Profile Interest Deleted: " + id);
		addProfileHistory(profileHistory);
		return result;
	}

	// thread safe - is per connection
	@SqlQuery("select last_insert_id();")
	public int lastInsertId();
}
