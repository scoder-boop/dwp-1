package local.dw.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import io.dropwizard.logback.shaded.checkerframework.checker.nullness.qual.NonNull;
import local.dw.api.Profile;

public class ProfileMapper implements RowMapper<Profile> {
	private static final String ID = "id";
	private static final String USERNAME = "username";
	private static final String FIRST_NAME = "first_name";
	private static final String LAST_NAME = "last_name";
	private static final String EMAIL = "email";
	private static final String ADDRESS = "address";
	private static final String PHONE_NUMBER = "phone_number";
	private static final String ACTIVE = "active";

	public ProfileMapper() {
		
	}
	
	@Override
	public Profile map(ResultSet resultSet, StatementContext statementContext) throws SQLException {
		return new Profile(resultSet.getInt(ID), 
				resultSet.getString(USERNAME), 
				resultSet.getString(FIRST_NAME),
				resultSet.getString(LAST_NAME),
				resultSet.getString(EMAIL),
				resultSet.getString(ADDRESS),
				resultSet.getInt(PHONE_NUMBER),
				resultSet.getBoolean(ACTIVE)
		);
	}
}
