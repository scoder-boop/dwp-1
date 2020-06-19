package local.dw.dao;

import local.dw.api.ProfileHistory;
import local.dw.api.Site;
import local.dw.mapper.SiteMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;

@RegisterRowMapper(SiteMapper.class)
public interface SiteDao {

    @SqlUpdate("insert into profile_history (id, profile_id, event_time, event_time_epoch, event) "
            + "values(:id, :profileId, :eventTime, :eventTimeEpoch, :event);")
    boolean addProfileHistory(@BindBean final ProfileHistory profileHistory);

    @SqlQuery("select id, name, site_type, connection_string, description, site_auth_details from site;")
    List<Site> getAllSites();

    @SqlQuery("select id, name, site_type, connection_string, description, site_auth_details from site where id = :id;")
    Site getSite(@Bind("id") final int id);

    @SqlUpdate("insert into site (id, name, site_type, connection_string, description, site_auth_details) "
            + "values(:id, :name, :site_type, :connection_string, :description, :site_auth_details);")
    boolean addSite(@BindBean final Site site);

    @Transaction
    default Site addSiteWithHistory(Site site) {
        addSite(site);
        int id = lastInsertId();
        ProfileHistory profileHistory = new ProfileHistory(site.getId(), "New site created " + site.toString());
        addProfileHistory(profileHistory);
        return getSite(id);
    }

    @SqlUpdate("update site "
            + "set name = coalesce(:name, name)"
            + ", site_type = coalesce(:site_type, site_type)"
            + ", connection_string = coalesce(:connection_string, connection_string) "
            + ", description = coalesce(:description, description) "
            + ", site_auth_details = coalesce(:siteAuthDetails, siteAuthDetails) "
            + "where id = :id;")
    boolean editSite(@BindBean final Site site);


    @Transaction
    default boolean editSiteWithHistory(Site site) {
        boolean result = editSite(site);
        ProfileHistory profileHistory = new ProfileHistory(site.getId(), "Site amended to " + site.toString());
        addProfileHistory(profileHistory);
        return result;
    }

    @SqlUpdate("delete from site where id = :id;")
    boolean deleteSite(@Bind("id") final int id);

    @Transaction
    default boolean deleteSiteWithHistory(final int id) {
        boolean result = deleteSite(id);
        ProfileHistory profileHistory = new ProfileHistory(id, "Site removed" );
        addProfileHistory(profileHistory);
        return result;
    }
    // thread safe - is per connection
    @SqlQuery("select last_insert_id();")
    public int lastInsertId();

}
