package local.dw.service;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import local.dw.api.ProfileHistory;
import local.dw.dao.ProfileHistoryDao;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;

import local.dw.api.Profile;
import local.dw.dao.ProfilesDao;

public class ProfilesService {

	private static final String PROFILE_NOT_FOUND = "Profile id %s not found.";
	private static final String DATABASE_REACH_ERROR = "Could not reach the MySQL database. The database may be down or there may be network connectivity issues. Details: ";
	private static final String DATABASE_CONNECTION_ERROR = "Could not create a connection to the MySQL database. The database configurations are likely incorrect. Details: ";
	private static final String DATABASE_UNEXPECTED_ERROR = "Unexpected error occurred while attempting to reach the database. Details: ";
	private static final String SUCCESS = "Success...";
	private static final String UNEXPECTED_ERROR = "An unexpected error occurred while deleting profile.";

//	@CreateSqlObject
//	abstract ProfilesDao profilesDao;
	private ProfilesDao profilesDao;

	@Inject
	public ProfilesService(final ProfilesDao profilesDao) {
		this.profilesDao = profilesDao;
	}

	public List<Profile> getProfiles() {
		return profilesDao.getProfiles();
	}

	public List<Profile> getAllProfiles() {
		return profilesDao.getAllProfiles();
	}

	public List<Profile> getInactiveProfiles() {
		return profilesDao.getInactiveProfiles();
	}

	public Profile getProfile(int id) {
		Profile profile = profilesDao.getProfile(id);
		if (Objects.isNull(profile)) {
			throw new WebApplicationException(String.format(PROFILE_NOT_FOUND, id), Status.NOT_FOUND);
		}
		return profile;
	}

	public Profile createProfile(final Profile profile) {
		//TODO make into transaction
		final int id =  profilesDao.addProfileWithHistory(profile);
		return profilesDao.getProfile(id);
	}

	public Profile editProfile(final Profile profile) {
		if (Objects.isNull(profilesDao.getProfile(profile.getId()))) {
			throw new WebApplicationException(String.format(PROFILE_NOT_FOUND, profile.getId()), Status.NOT_FOUND);
		}
		boolean result = profilesDao.editProfileWithHistory(profile);
		if (!result) {
			throw new WebApplicationException(String.format(PROFILE_NOT_FOUND, profile.getId()), Status.NOT_FOUND);
		}
		return profilesDao.getProfile(profile.getId());
	}

	public String deleteProfile(final int id) {
		boolean result = profilesDao.deleteProfileWithHistory(id);
		if (result) {
			return SUCCESS;
		}
		//or just return FAIL
		throw new WebApplicationException(String.format(PROFILE_NOT_FOUND, id), Status.NOT_FOUND);
	}

	public String performHealthCheck() {
		try {
			profilesDao.getProfiles();
		} catch (UnableToExecuteStatementException ex) {
			return checkUnableToExecuteStatementException(ex);
		} catch (Exception ex) {
			return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();
		}
		return null;
	}

	/*
	 * private String
	 * checkUnableToObtainConnectionException(UnableToObtainConnectionException ex)
	 * { if (ex.getCause() instanceof java.sql.SQLNonTransientConnectionException) {
	 * return DATABASE_REACH_ERROR + ex.getCause().getLocalizedMessage(); } else if
	 * (ex.getCause() instanceof java.sql.SQLException) { return
	 * DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage(); } else {
	 * return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage(); } }
	 */
	private String checkUnableToExecuteStatementException(UnableToExecuteStatementException ex) {
		if (ex.getCause() instanceof java.sql.SQLSyntaxErrorException) {
			return DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
		} else {
			return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();
		}
	}
}
