package local.dw.mapper;

import local.dw.api.ProfileHistory;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileHistoryMapper implements RowMapper<ProfileHistory> {
    private static final String ID = "id";
    private static final String PROFILE_ID = "profile_id";
    private static final String EVENT_TIME = "event_time";
    private static final String EVENT_TIME_EPOCH = "event_time_epoch";
    private static final String EVENT = "event";

    public ProfileHistoryMapper() {
    }

    @Override
    public ProfileHistory map(ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new ProfileHistory(resultSet.getInt(ID),
                resultSet.getInt(PROFILE_ID),
                resultSet.getTimestamp(EVENT_TIME),
                resultSet.getLong(EVENT_TIME_EPOCH),
                resultSet.getString(EVENT)
        );
    }
}
