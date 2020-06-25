package local.dw;

import java.util.EnumSet;

import javax.inject.Singleton;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import local.dw.api.ProfileInterest;
import local.dw.api.ProfileSite;
import local.dw.dao.*;
import local.dw.resources.ProfileInterestResource;
import local.dw.resources.SiteResource;
import local.dw.service.ProfileInterestService;
import local.dw.service.ProfileSiteService;
import local.dw.service.SiteService;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import io.dropwizard.Application;
import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.servlets.CacheBustingFilter;
import io.dropwizard.setup.Environment;
import local.dw.health.NoisyHealthCheck;
//import local.dw.resources.ProfilesResource;
import local.dw.resources.ProfilesResourceWithService;
import local.dw.service.ProfilesService;

public class NoisyApplication extends Application<NoisyConfiguration> {

	private static final String SQL = "sql";

	public static void main(String[] args) throws Exception {
		new NoisyApplication().run(args);
	}

	@Override
	public void run(NoisyConfiguration configuration, Environment environment) {

		environment.servlets().addFilter("CacheBustingFilter", new CacheBustingFilter())
				.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
		enableCorsHeaders(environment);

		// Datasource configuration
		final ManagedDataSource dataSource = configuration.getDataSourceFactory().build(environment.metrics(), SQL);
		final JdbiFactory factory = new JdbiFactory();
		final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), dataSource, "noisy");
		// jdbi 3 requirement for SQL
		jdbi.installPlugin(new SqlObjectPlugin());

		final ProfilesDao profilesDao = jdbi.onDemand(ProfilesDao.class);
		final ProfileSiteDao profileSiteDao = jdbi.onDemand(ProfileSiteDao.class);
		final ProfileInterestDao profileInterestDao = jdbi.onDemand(ProfileInterestDao.class);
		final SiteDao siteDao = jdbi.onDemand(SiteDao.class);

		// extra
		environment.jersey().register(new AbstractBinder() {
			@Override
			public void configure() {
				bindAsContract(ProfilesDao.class).in(Singleton.class);
				bindAsContract(ProfilesService.class).in(Singleton.class);
				bindAsContract(ProfileInterestService.class).in(Singleton.class);
				bindAsContract(SiteService.class).in(Singleton.class);
			}
		});

		final ProfilesService profilesService = new ProfilesService(profilesDao);
		final ProfileInterestService profileInterestService = new ProfileInterestService(profileInterestDao);
		final ProfileSiteService profileSiteService = new ProfileSiteService(profileSiteDao);
		final SiteService siteService = new SiteService(siteDao);

		environment.healthChecks().register("database", new NoisyHealthCheck(profilesDao));
		environment.jersey().register(new ProfilesResourceWithService(profilesService, profileSiteService));
		environment.jersey().register(new ProfileInterestResource(profileInterestService));
		environment.jersey().register(new SiteResource(siteService));

		/*
		 * environment.jersey() .register(new AuthDynamicFeature( new
		 * BasicCredentialAuthFilter.Builder<User>() .setAuthenticator(new
		 * NoisyAuthenticator()) .setAuthorizer(new NoisyAuthorizer())
		 * .setPrefix(BEARER) .buildAuthFilter()));
		 * 
		 * environment.jersey().register(RolesAllowedDynamicFeature.class);
		 * environment.jersey().register(new
		 * AuthValueFactoryProvider.Binder<>(User.class));
		 */
	}

	private void enableCorsHeaders(Environment environment) {
		final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

		// Configure CORS parameters
		cors.setInitParameter("allowedOrigins", "*");
		cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
		cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
		cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
		cors.setInitParameter(CrossOriginFilter.CHAIN_PREFLIGHT_PARAM, Boolean.FALSE.toString());

	}

}
