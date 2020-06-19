package local.dw.mapper;

import local.dw.api.FrequencyType;
import local.dw.api.ProfileSite;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ProfileSiteMapper implements RowMapper<ProfileSite> {
    private static final String PROFILE_ID = "profile_id";
    private static final String SITE_ID = "site_id";
    private static final String FREQUENCY_TYPE = "frequency_type";
    private static final String FREQUENCY = "frequency";
    private static final String LOGIN_STRING = "login_string";
    private static final String ACTIVE = "active";
    private static final String PASSWORD_REQUIRED = "password_required";
    private static final String PASSWORD = "password";
    private static final String AUTH_DETAILS = "auth_details";

    public ProfileSiteMapper() {
    }

    @Override
    public ProfileSite map(ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new ProfileSite(resultSet.getInt(PROFILE_ID),
                resultSet.getInt(SITE_ID),
                FrequencyType.values()[resultSet.getInt(FREQUENCY_TYPE)],
                resultSet.getInt(FREQUENCY),
                resultSet.getString(LOGIN_STRING),
                resultSet.getBoolean(ACTIVE),
                resultSet.getBoolean(PASSWORD_REQUIRED),
                resultSet.getString(PASSWORD),
                resultSet.getString(AUTH_DETAILS)
        );
    }
}
