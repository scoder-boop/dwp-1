package local.dw.dao;

import local.dw.api.Site;
import local.dw.mapper.SiteMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterRowMapper(SiteMapper.class)
public interface SiteDao {
    @SqlQuery("select id, site_type, connection_string, description from site;")
    public List<Site> getAllSites();

    @SqlQuery("select id, site_type, connection_string, description from site where id = :id;")
    public Site getSite(@Bind("id") final int id);

    @SqlUpdate("insert into site (id, site_type, connection_string, description) "
            + "values(:id, :site_type, :connection_string, :description);")
    public boolean addSite(@BindBean final Site site);

    @SqlUpdate("update site "
            + "set site_type = coalesce(:site_type, site_type)"
            + ", connection_string = coalesce(:connection_string, connection_string) "
            + ", description = coalesce(:description, description) "
            + "where id = :id;")
    public boolean editSite(@BindBean final Site site);

    @SqlUpdate("delete from site where id = :id;")
    public boolean deleteSite(@Bind("id") final int id);
}
