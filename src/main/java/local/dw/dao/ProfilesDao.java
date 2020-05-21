package local.dw.dao;

import java.util.List;

import org.jdbi.v3.core.result.ResultIterable;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import local.dw.mapper.ProfileMapper;

import local.dw.api.Profile;

@RegisterRowMapper(ProfileMapper.class)
public interface ProfilesDao {
	
	  @SqlQuery("select id, username, first_name, last_name, email, address, phone_number, active from profile where active = true;")
	  public List<Profile> getProfiles();
//	  public ResultIterable<Profile> getProfilesAsIterable();

	  @SqlQuery("select id, username, first_name, last_name, email, address, phone_number, active from profile where active = false;")
	  public List<Profile> getInactiveProfiles();

	  @SqlQuery("select id, username, first_name, last_name, email, address, phone_number, active from profile where id = :id")
	  public Profile getProfile(@Bind("id") final int id);

	  @SqlUpdate("insert into profile(username, first_name, last_name, email, address, phone_number, active) "
	  		+ "values(:username, :firstName, :lastName, :email, :address, :phoneNumber, true);")
	  public boolean createProfile(@BindBean final Profile profile);

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

	  @SqlUpdate("delete from profile where id = :id;")
	  public boolean deleteProfile(@Bind("id") final int id);

	  @SqlQuery("select last_insert_id();")
	  public int lastInsertId();

}
