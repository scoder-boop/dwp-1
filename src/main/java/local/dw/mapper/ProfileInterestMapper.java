package local.dw.mapper;

import local.dw.api.ProfileInterest;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileInterestMapper implements RowMapper<ProfileInterest> {
	private static final String ID = "id";
	private static final String PROFILE_ID = "profile_id";
	private static final String TOPIC = "topic";
	private static final String ACTIVE = "active";

	public ProfileInterestMapper() {
		
	}
	
	@Override
	public ProfileInterest map(ResultSet resultSet, StatementContext statementContext) throws SQLException {
		return new ProfileInterest(resultSet.getInt(ID),
				resultSet.getInt(PROFILE_ID),
				resultSet.getString(TOPIC),
				resultSet.getBoolean(ACTIVE)
		);
	}
}
