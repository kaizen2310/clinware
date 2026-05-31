package com.example.weather;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/activity")
public class ActivityResource {

    @Inject
    ActivityService activityService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getRecommendation(
            @QueryParam("lat") double lat,
            @QueryParam("lon") double lon) {
        return activityService.getRecommendation(lat, lon);
    }

    /**
     * TODO: TASK 5 - Status Endpoint
     * Implement an endpoint that returns "READY" if the service is configured correctly,
     * otherwise return "NOT_CONFIGURED".
     */
    @GET
    @Path("/status")
    @Produces(MediaType.TEXT_PLAIN)
    public String getStatus() {
        return "NOT_CONFIGURED";
    }
}
