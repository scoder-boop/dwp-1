package local.dw.dao;

import local.dw.api.ProfileHistory;
import local.dw.api.Site;
import local.dw.mapper.ProfileHistoryMapper;
import local.dw.mapper.SiteMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterRowMapper(ProfileHistoryMapper.class)
public interface ProfileHistoryDao {

    @SqlQuery("select id, profile_id, event_time, event_time_epoch, event from profile_history where profile_id = :profile_id order by id;")
    public ProfileHistory getHistoryForProfile(@Bind("profile_id") final int profileId);

    @SqlUpdate("insert into profile_history (id, profile_id, event_time, event_time_epoch, event) "
            + "values(:id, :profileId, :eventTime, :eventTimeEpoch, :event);")
    public boolean addProfileHistory(@BindBean final ProfileHistory profileHistory);
}
