package local.dw.health;

import com.codahale.metrics.health.HealthCheck;

import local.dw.dao.ProfilesDao;

public class NoisyHealthCheck extends HealthCheck {

	private static final String HEALTHY = "The Database Service is healthy for read and write";
	private static final String UNHEALTHY = "The Database Service is not healthy. ";
	private static final String MESSAGE_PLACEHOLDER = "{}";

	private final ProfilesDao profilesDao;

	public NoisyHealthCheck(ProfilesDao profilesDao) {
		this.profilesDao = profilesDao;
	}

	@Override
	public Result check() {

		Object mySqlHealthStatus = null;
		try {
			this.profilesDao.getProfiles();
		} catch (Exception e) {
			mySqlHealthStatus = e;
		}

		if (mySqlHealthStatus == null) {
			return Result.healthy(HEALTHY);
		} else {
			return Result.unhealthy(UNHEALTHY + MESSAGE_PLACEHOLDER, mySqlHealthStatus);
		}
	}
}
