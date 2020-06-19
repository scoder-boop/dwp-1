package local.dw.service;


import local.dw.api.Profile;
import local.dw.api.ProfileSite;
import local.dw.dao.ProfileSiteDao;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

public class ProfileSiteService {
    private static final String PROFILE_SITE_NOT_FOUND = "Profile id %s : Site id %s not found.";

    private ProfileSiteDao profileSiteDao;

    @Inject
    public ProfileSiteService(final ProfileSiteDao profileSiteDao) {
        this.profileSiteDao = profileSiteDao;
    }

    public List<ProfileSite> getProfileSites(final int id) {
        return profileSiteDao.getProfileSites(id);
    }

    public List<ProfileSite> getProfileSite(final int profileId, final int siteId) {
        return profileSiteDao.getProfileSite(profileId, siteId);
    }

    public List<ProfileSite>  createProfileSite(final ProfileSite profileSite) {
        //TODO make into transaction
        final boolean result =  profileSiteDao.addProfileSiteWithHistory(profileSite);
        return profileSiteDao.getProfileSites(profileSite.getProfileId());
    }

    public List<ProfileSite> editProfileSite(final ProfileSite profileSite) {
        if (Objects.isNull(profileSiteDao.getProfileSite(profileSite.getProfileId(), profileSite.getSiteId()))) {
            throw new WebApplicationException(String.format(PROFILE_SITE_NOT_FOUND, profileSite.getProfileId(), profileSite.getSiteId()), Response.Status.NOT_FOUND);
        }
        boolean result = profileSiteDao.editProfileSiteWithHistory(profileSite);

        if (!result) {
            throw new WebApplicationException(String.format(PROFILE_SITE_NOT_FOUND, profileSite.getProfileId(), profileSite.getSiteId()), Response.Status.NOT_FOUND);
        }

        return profileSiteDao.getProfileSites(profileSite.getProfileId());
    }

}
