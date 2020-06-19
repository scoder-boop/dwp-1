package local.dw.dao;

import local.dw.api.Profile;
import local.dw.api.ProfileHistory;
import local.dw.api.ProfileSite;
import local.dw.mapper.ProfileSiteMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;

@RegisterRowMapper(ProfileSiteMapper.class)
public interface ProfileSiteDao {

    @SqlUpdate("insert into profile_history (id, profile_id, event_time, event_time_epoch, event) "
            + "values(:id, :profileId, :eventTime, :eventTimeEpoch, :event);")
    public boolean addProfileHistory(@BindBean final ProfileHistory profileHistory);


    @SqlQuery("select profile_id, site_id, frequency_type, frequency, login_string, active, password_required, password from profileSite;")
    public List<ProfileSite> getAllProfileSites();


    @SqlQuery("select profile_id, site_id, frequency_type, frequency, login_string, active, password_required, password " +
            "from profileSite " +
            "where profile_id = :profileId and site_id = :siteId;")
    List<ProfileSite> getProfileSite(int profileId, int siteId);

    @SqlQuery("select profile_id, site_id, frequency_type, frequency, login_string, active, password_required, password from profileSite where id = :id;")
    public List<ProfileSite> getProfileSites(@Bind("id") final int id);

    @SqlUpdate("insert into profileSite (profile_id, site_id, frequency_type, frequency, login_string, active, password_required, password) "
            + "values(:profile_id, :site_id, :frequency_type, :frequency, :login_string, :active, :password_required, :password);")
    public boolean addProfileSite(@BindBean final ProfileSite profileSite);

    @Transaction
    default boolean addProfileSiteWithHistory(ProfileSite profileSite) {
        boolean result = addProfileSite(profileSite);
        ProfileHistory profileHistory = new ProfileHistory(profileSite.getProfileId(), "Site added to Profile " + profileSite.toString());
        addProfileHistory(profileHistory);
        return result;
    }

    @SqlUpdate("update profileSite "
            + "set frequency_type = coalesce(:frequency_type, frequency_type)"
            + ", frequency = coalesce(:frequency, frequency) "
            + ", login_string = coalesce(:login_string, login_string) "
            + ", active = coalesce(:active, active) "
            + ", password_required = coalesce(:password_required, password_required) "
            + ", password = coalesce(:password, password) "
            + "where profile_id = :profile_id "
            + "and site_id = :site_id;")
    public boolean editProfileSite(@BindBean final ProfileSite profileSite);

    @Transaction
    default boolean editProfileSiteWithHistory(ProfileSite profileSite) {
        boolean result = editProfileSite(profileSite);
        ProfileHistory profileHistory = new ProfileHistory(profileSite.getProfileId(), "Site changed for Profile " + profileSite.toString());
        addProfileHistory(profileHistory);
        return result;
    }

    @SqlUpdate("delete from profileSite where profile_id = :profile_id and site_id = :site_id;")
    public boolean deleteProfileSite(@Bind("profile_id") final int profileId, @Bind("site_id") final int siteId);

    @Transaction
    default boolean deleteProfileSiteWithHistory(final int profileId, final int siteId) {
        boolean result = deleteProfileSite(profileId, siteId);
        ProfileHistory profileHistory = new ProfileHistory(profileId, "Site removed from Profile " + siteId);
        addProfileHistory(profileHistory);
        return result;
    }
}
