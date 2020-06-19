package local.dw.service;

import local.dw.api.Site;
import local.dw.dao.SiteDao;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

public class SiteService {
    private static final String SUCCESS = "200";
    private static final String SITE_NOT_FOUND = "Site id %s not found.";

    private SiteDao siteDao;

    @Inject
    public SiteService(final SiteDao siteDao) {
        this.siteDao = siteDao;
    }

    public Site getSite(int id) {
        return siteDao.getSite(id);
    }

    public List<Site> getAllSites() {
        return siteDao.getAllSites();
    }

    public Site addSite(final Site site) {
        return siteDao.addSiteWithHistory(site);
     }

    public Site editSite(final Site site) {
        final boolean result = siteDao.editSiteWithHistory(site);
        if (!result) {
            throw new WebApplicationException(String.format(SITE_NOT_FOUND, site.getId()), Response.Status.NOT_FOUND);
        }
        return siteDao.getSite(site.getId());
    }
}
