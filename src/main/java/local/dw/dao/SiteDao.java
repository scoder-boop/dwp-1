package local.dw.dao;

import local.dw.api.ProfileHistory;
import local.dw.api.Site;
import local.dw.api.SiteHistory;
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

    @SqlUpdate("insert into site_history (id, site_id, event_time, event_time_epoch, event) "
            + "values(:id, :siteId, :eventTime, :eventTimeEpoch, :event);")
    boolean addSiteHistory(@BindBean final SiteHistory siteHistory);

    @SqlQuery("select id, name, site_type, connection_string, description, site_auth_details, accept_xpath, useable from site;")
    List<Site> getAllSites();

    @SqlQuery("select id, name, site_type, connection_string, description, site_auth_details, accept_xpath, useable from site where id = :id;")
    Site getSite(@Bind("id") final int id);

    @SqlUpdate("insert into site (id, name, site_type, connection_string, description, site_auth_details, accept_xpath, useable) "
            + "values(:id, :name, :siteType, :connectionString, :description, :siteAuthDetails, :acceptXpath, :useable);")
    boolean addSite(@BindBean final Site site);

    @Transaction
    default Site addSiteWithHistory(Site site) {
        addSite(site);
        int id = lastInsertId();
        site.setId(id);
        SiteHistory siteHistory = new SiteHistory(site.getId(), "New site created " + site.toString());
        addSiteHistory(siteHistory);
        return getSite(id);
    }

    @SqlUpdate("update site "
            + "set name = coalesce(:name, name)"
            + ", site_type = coalesce(:siteType, siteType)"
            + ", connection_string = coalesce(:connectionString, connectionString) "
            + ", description = coalesce(:description, description) "
            + ", site_auth_details = coalesce(:siteAuthDetails, siteAuthDetails) "
            + ", accept_xpath = coalesce(:acceptXpath, acceptXpath) "
            + ", useable = coalesce(:useable, useable) "
            + "where id = :id;")
    boolean editSite(@BindBean final Site site);


    @Transaction
    default boolean editSiteWithHistory(Site site) {
        boolean result = editSite(site);
        SiteHistory siteHistory = new SiteHistory(site.getId(), "Site amended to " + site.toString());
        addSiteHistory(siteHistory);
        return result;
    }

    @SqlUpdate("delete from site where id = :id;")
    boolean deleteSite(@Bind("id") final int id);

    @Transaction
    default boolean deleteSiteWithHistory(final int id) {
        boolean result = deleteSite(id);
        SiteHistory siteHistory = new SiteHistory(id, "Site removed" );
        addSiteHistory(siteHistory);
        return result;
    }
    // thread safe - is per connection
    @SqlQuery("select last_insert_id();")
    public int lastInsertId();

}
