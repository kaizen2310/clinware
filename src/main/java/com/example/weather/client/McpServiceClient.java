package com.example.weather.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * TODO: TASK 4 - Implement the MCP client to fetch personalization data.
 * This client should connect to a public hosted MCP-compliant server (or simulated one).
 * For this assignment, we will use a simple echo service to simulate MCP tool results.
 */
@RegisterRestClient(configKey = "mcp-api")
public interface McpServiceClient {

    @GET
    @Path("/get")
    String getMcpGreeting();
}
