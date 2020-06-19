package local.dw.resources;

import com.codahale.metrics.annotation.Timed;
import local.dw.api.Site;
import local.dw.api.Representation;
import local.dw.service.SiteService;
import org.eclipse.jetty.http.HttpStatus;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/sites")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("ADMIN")
public class SiteResource {

    private SiteService siteService;

    @Inject
    public SiteResource(SiteService siteService) {
        this.siteService = siteService;
    }

    @GET
    @Timed
    @Path("/all")
    public Representation<List<Site>> getAllSites() {
        List<Site> sites = siteService.getAllSites();
        return new Representation<List<Site>>(HttpStatus.OK_200, sites);
    }

    @GET
    @Timed
    @Path("{id}")
    public Representation<Site> getSite(@PathParam("id") final int id) {
        Site site = siteService.getSite(id);
        return new Representation<Site>(HttpStatus.OK_200, site);
    }

    @POST
    @Timed
    public Representation<Site> createSite(@NotNull @Valid final Site site) {
        return new Representation<Site>(HttpStatus.OK_200, siteService.addSite(site));
    }

    @PUT
    @Timed
    @Path("{id}")
    public Representation<Site> editSite(@NotNull @Valid final Site site, @PathParam("id") final int id) {
        //TODO why?
        site.setId(id);
        return new Representation<Site>(HttpStatus.OK_200, siteService.editSite(site));
    }

}
