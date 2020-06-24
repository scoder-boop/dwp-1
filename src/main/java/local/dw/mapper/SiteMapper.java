package local.dw.mapper;

import local.dw.api.Site;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SiteMapper implements RowMapper<Site> {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SITE_TYPE = "site_type";
    private static final String CONNECTION_STRING = "connection_string";
    private static final String DESCRIPTION = "description";
    private static final String SITE_AUTH = "site_auth_details";
    private static final String ACCEPT_XPATH = "accept_xpath";
    private static final String USEABLE = "useable";

    public SiteMapper() {
    }

    @Override
    public Site map(ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Site(resultSet.getInt(ID),
                resultSet.getString(NAME),
                resultSet.getString(SITE_TYPE),
                resultSet.getString(CONNECTION_STRING),
                resultSet.getString(DESCRIPTION),
                resultSet.getString(SITE_AUTH),
                resultSet.getString(ACCEPT_XPATH),
                resultSet.getBoolean(USEABLE)
        );
    }
}
