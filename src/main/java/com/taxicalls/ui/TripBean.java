package com.taxicalls.ui;

import com.taxicalls.protocol.Response;
import com.taxicalls.ui.model.Trip;
import com.taxicalls.utils.ServiceRegistry;
import java.util.Collection;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;

@Model
public class TripBean {

    @Inject
    private ServiceRegistry serviceRegistry;

    public Collection<Trip> getTrips() {
        Response response = ClientBuilder.newClient()
                .target(serviceRegistry.discoverServiceURI("TripService")).path("trips")
                .request().get(Response.class);
        return (Collection<Trip>) response.getEntity();
    }

}
