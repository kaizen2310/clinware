package com.example.weather;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Optional;

@Path("/activity")
public class ActivityResource {

    @Inject
    ActivityService activityService;

    /**
     * TASK 5 - Inject both REST client URLs from application.properties.
     * If both are present and non-blank, the service is considered configured.
     */
    @ConfigProperty(name = "weather-api/mp-rest/url")
    Optional<String> weatherApiUrl;

    @ConfigProperty(name = "mcp-api/mp-rest/url")
    Optional<String> mcpApiUrl;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getRecommendation(
            @QueryParam("lat") double lat,
            @QueryParam("lon") double lon) {
        return activityService.getRecommendation(lat, lon);
    }

    /**
     * TASK 5 - Status health-check endpoint.
     * Returns "READY" if both external service URLs are configured, else "NOT_CONFIGURED".
     */
    @GET
    @Path("/status")
    @Produces(MediaType.TEXT_PLAIN)
    public String getStatus() {
        boolean weatherConfigured = weatherApiUrl.isPresent() && !weatherApiUrl.get().isBlank();
        boolean mcpConfigured = mcpApiUrl.isPresent() && !mcpApiUrl.get().isBlank();

        if (weatherConfigured && mcpConfigured) {
            return "READY";
        }
        return "NOT_CONFIGURED";
    }
}