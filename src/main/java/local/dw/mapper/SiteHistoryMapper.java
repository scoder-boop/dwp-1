package local.dw.mapper;

import local.dw.api.SiteHistory;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SiteHistoryMapper implements RowMapper<SiteHistory> {
    private static final String ID = "id";
    private static final String SITE_ID = "site_id";
    private static final String EVENT_TIME = "event_time";
    private static final String EVENT_TIME_EPOCH = "event_time_epoch";
    private static final String EVENT = "event";

    public SiteHistoryMapper() {
    }

    @Override
    public SiteHistory map(ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new SiteHistory(resultSet.getInt(ID),
                resultSet.getInt(SITE_ID),
                resultSet.getTimestamp(EVENT_TIME),
                resultSet.getLong(EVENT_TIME_EPOCH),
                resultSet.getString(EVENT)
        );
    }
}
