package local.dw.service;

import local.dw.api.ProfileInterest;
import local.dw.dao.ProfileInterestDao;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import java.util.List;
import java.util.Objects;

public class ProfileInterestService {

	private static final String PROFILE_NOT_FOUND = "ProfileInterest id %s not found.";
	private static final String DATABASE_REACH_ERROR = "Could not reach the MySQL database. The database may be down or there may be network connectivity issues. Details: ";
	private static final String DATABASE_CONNECTION_ERROR = "Could not create a connection to the MySQL database. The database configurations are likely incorrect. Details: ";
	private static final String DATABASE_UNEXPECTED_ERROR = "Unexpected error occurred while attempting to reach the database. Details: ";
	private static final String SUCCESS = "Success...";
	private static final String UNEXPECTED_ERROR = "An unexpected error occurred while deleting profileInterest.";

	private ProfileInterestDao profileInterestDao;

	@Inject
	public ProfileInterestService(final ProfileInterestDao profileInterestDao) {
		this.profileInterestDao = profileInterestDao;
	}

	public List<ProfileInterest> getAllInterestsForProfile(int id) {
		List<ProfileInterest> profileInterests = profileInterestDao.getAllInterestsForProfile(id);
		if (Objects.isNull(profileInterests)) {
			throw new WebApplicationException(String.format(PROFILE_NOT_FOUND, id), Status.NOT_FOUND);
		}
		return profileInterests;
	}

	public List<ProfileInterest> createProfileInterest(final ProfileInterest profileInterest) {
		return profileInterestDao.addProfileInterestWithHistory(profileInterest);
	}

	public ProfileInterest editProfileInterest(final ProfileInterest profileInterest) {
		if (Objects.isNull(profileInterestDao.getProfileInterest(profileInterest.getId()))) {
			throw new WebApplicationException(String.format(PROFILE_NOT_FOUND, profileInterest.getId()), Status.NOT_FOUND);
		}
		boolean result = profileInterestDao.editProfileInterestWithHistory(profileInterest);
		if (!result) {
			throw new WebApplicationException(String.format(PROFILE_NOT_FOUND, profileInterest.getId()), Status.NOT_FOUND);
		}
		return profileInterest;
	}

	public String deleteProfileInterest(final int id) {
		boolean result = profileInterestDao.deleteProfileInterestWithHistory(id);
		if (result) {
			return SUCCESS;
		}
		//or just return FAIL
		throw new WebApplicationException(String.format(PROFILE_NOT_FOUND, id), Status.NOT_FOUND);
	}

	private String checkUnableToExecuteStatementException(UnableToExecuteStatementException ex) {
		if (ex.getCause() instanceof java.sql.SQLSyntaxErrorException) {
			return DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
		} else {
			return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();
		}
	}
}
