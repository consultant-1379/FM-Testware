/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/

package example.rest.resources;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;

import com.ericsson.oss.itpf.sdk.eventbus.model.EventSender;
import com.ericsson.oss.itpf.sdk.eventbus.model.annotation.Modeled;

import example.api.ServiceModeledEvent;
import example.impl.ServiceStatisticsBean;

/*
 * Rest Services 
 *  
 * Resources are served relative to the servlet path specified in the {@link ApplicationPath}
 * annotation.
 * 
 */
@Path("/resource")
@RequestScoped
public class DateRestResource {

    @Inject
    private Logger logger;

    @Inject
    private ServiceStatisticsBean stats;

    @Inject
    @Modeled
    private EventSender<ServiceModeledEvent> eventSender;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("send")
    public String sendMessage() {
        this.logger.debug("Send message...");
        this.eventSender.send(new ServiceModeledEvent());
        this.stats.setNumberOfRequests(this.stats.getNumberOfRequests() + 1);
        return "ok";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getStats")
    public String getStats() {
        return "Sent " + this.stats.getNumberOfRequests();
    }

}